<h3>It is now <%= new java.util.Date() %></h3>
<input type="button" value="Show All books" onclick='ajaxResult("All", "book-result")'/>
<div id="book-result"></div>
<input type="button" value="Show All books JSON" onclick='ajaxResult("json", "book-result-json")'/>
<div id="book-result-json"></div>
<input type="button" value="Show All books XML" onclick='ajaxResult("xml", "book-result-xml")'/>
<div id="book-result-xml"></div>