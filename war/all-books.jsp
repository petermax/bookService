<h3>It is now <%= new java.util.Date() %></h3>
<input type="button" value="Show All books" onclick='stringBookTable("BookDetails", "book-result")'/>
<div id="book-result"></div>
<input type="button" value="Show All books JSON" onclick='jsonBookTable("BookDetails", "book-result-json")'/>
<div id="book-result-json"></div>
<input type="button" value="Show All books XML" onclick='xmlBookTable("BookDetails", "book-result-xml")'/>
<div id="book-result-xml"></div>