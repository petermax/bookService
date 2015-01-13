<h3>It is now <%= new java.util.Date() %></h3>

<form action="#">
	Search: <input type="text" id="phrase">
	<input type="button" value="Search books" onclick='jsonBookSearch("phrase", "book-result")'/>
</form>
<div id="book-result"></div>