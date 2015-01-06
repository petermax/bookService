// Get the browser-specific request object, either for
// Firefox, Safari, Opera, Mozilla, Netscape, IE 7, or IE 8 
// (top entry) or for IE 5.5 and 6 (second entry). 

function getRequestObject() {
  if (window.XMLHttpRequest) {
    return(new XMLHttpRequest());
  } else if (window.ActiveXObject) { 
    return(new ActiveXObject("Microsoft.XMLHTTP"));
  } else {
    return(null); 
  }
}

// Make an HTTP request to the given address. 
// Display result in the HTML element that has given ID.

function ajaxResult(format, resultRegion) {
  var request = getRequestObject();
  request.onreadystatechange = 
    function() { 
	  showResponseText(request, resultRegion); 
	};
  request.open("GET", format, true);
  request.send();

}

//Put response text in the HTML element that has given ID.

function showResponseText(request, resultRegion) {
  if ((request.readyState == 4) &&
      (request.status == 200)) {
    htmlInsert(resultRegion, request.responseText);
  }
}

//Insert the HTML data into the element that has the specified id.

function htmlInsert(id, htmlData) {
  document.getElementById(id).innerHTML = htmlData;
}

//Generalized version of ajaxResultPost. In this
//version, you pass in a response handler function
//instead of just a result region.

function ajaxPost(address, data, responseHandler) {
	var request = getRequestObject();
	request.onreadystatechange = function() { responseHandler(request); };
	request.open("POST", address, true);
	request.setRequestHeader("Content-Type", 
                        "application/x-www-form-urlencoded");
	request.send(data);
}


function jsonBookTable(inputField, resultRegion) {
	  var address = "show-books";
	  var data = "book=" + inputField +
	             "&format=json";
	  ajaxPost(address, data, 
	           function(request) { 
	             showJsonBookInfo(request, resultRegion); 
	           });
}

function showJsonBookInfo(request, resultRegion) {
	  if ((request.readyState == 4) &&
	      (request.status == 200)) {
	    var rawData = request.responseText;
	    var data = eval(rawData);
	    var headings = ["Author","Title","ISBN"];
	    var table = getTable(headings, data);
	    htmlInsert(resultRegion, table);
	  }
}

function xmlBookTable(inputField, resultRegion) {
	  var address = "show-books";
	  var data = "book=" + inputField +
	             "&format=xml";
	  ajaxPost(address, data, 
	           function(request) { 
	             showXMLBookInfo(request, resultRegion); 
	           });
}

function showXMLBookInfo(request, resultRegion) {
	  if ((request.readyState == 4) &&
	      (request.status == 200)) {
		var xmlDocument = request.responseXML;
		var headings = ["Author","Title","ISBN"];
	    var books = xmlDocument.getElementsByTagName("Book");
	    var rows = new Array(books.length);
	    var subElementNames = ["author", "title", "isbn"];
	    for(var i = 0; i<books.length; i++) {
	      rows[i] = getElementValues(books[i], subElementNames);
	    }
	    var table = getTable(headings, rows);
	    //var table = "<table border='1'><tr><th>" + rows + "</th></tr></table>";
	    htmlInsert(resultRegion, table);
	  }
}

//Given an element object and an array of sub-element names,
//returns an array of the values of the sub-elements.
//E.g., for <foo><a>one</a><c>two</c><b>three</b></foo>,
//if the element points at foo,
//getElementValues(element, ["a", "b", "c"]) would return
//["one", "three", "two"]

function getElementValues(element, subElementNames) {
	var values = new Array(subElementNames.length);
	for(var i=0; i<subElementNames.length; i++) {
	 var name = subElementNames[i];
	 var subElement = element.getElementsByTagName(name)[0];
	 values[i] = getBodyContent(subElement);
	}
	return(values);
}

//Given an element, returns the body content.

function getBodyContent(element) {
  element.normalize();
  return(element.childNodes[0].nodeValue);
}

function stringBookTable(inputField, resultRegion) {
	  var address = "show-books";
	  var data = "book=" + inputField +
	             "&format=string";
	  ajaxPost(address, data, 
	           function(request) { 
	             showStringBookInfo(request, resultRegion); 
	           });
	}

function showStringBookInfo(request, resultRegion) {
  if ((request.readyState == 4) &&
      (request.status == 200)) {
    var rawData = request.responseText;
    rawData = rawData.replace("[","");
    rawData = rawData.replace("#]","");
    var rowStrings = rawData.split("#,");
    var headings = ["ID","Author","Title","ISBN"];
    var rows = new Array(rowStrings.length-1);
    for(var i=0; i<rowStrings.length; i++) {
      rows[i] = rowStrings[i].split(",");
    }
    var table = getTable(headings, rows);
    htmlInsert(resultRegion, table);
  }
}

//Takes as input an array of headings (to go into th elements)
//and an array-of-arrays of rows (to go into td
//elements). Builds an xhtml table from the data.

function getTable(headings, rows) {
var table = "<table border='1' class='ajaxTable'>\n" +
           getTableHeadings(headings) +
           getTableBody(rows) +
           "</table>";
return(table);
}

function getTableHeadings(headings) {
	  var firstRow = "  <tr>";
	  for(var i=0; i<headings.length; i++) {
	    firstRow += "<th>" + headings[i] + "</th>";
	  }
	  firstRow += "</tr>\n";
	  return(firstRow);
}

function getTableBody(rows) {
	var body = "";
	  for(var i=0; i<rows.length; i++) {
	    body += "  <tr>";
	    var row = rows[i];
	    if (row == "[object Object]"){
	    	body += "<td>" + row.author + "</td><td>" + row.title + "</td><td>" + row.isbn + "</td>";
	    } else {
		    for(var j=0; j<row.length; j++) {
		      body += "<td>" + row[j] + "</td>";
		    }
	    }
	    body += "</tr>\n";
	  }
  return(body);
}