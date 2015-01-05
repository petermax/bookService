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
request.onreadystatechange = 
 function() { responseHandler(request); };
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
  for(i = 0; i < rows.length; i++) {
      body += '<tr><th>' + rows[i].author + '</th><th>' + rows[i].title + '</th><th>' + rows[i].isbn + '</th></tr>';
  }
  return(body);
}