<%@ page import="ir.fathi.library.model.Book" %>
<%@ page import="java.util.List" %>
<style>
    .bodyContent {
        margin: 0;
        height: 90%;
    }
    aside {
        margin: 0;
        width: 40%;
        height: 100%;
        float: left;
        background-image: url("https://tiktarh.com/wp-content/uploads/2021/06/Cart1002491www.tiktarh.com_.jpg");
        background-size: 100%;
    }

    section {
        margin: 0;
        width: 60%;
        height: 100%;
        float: right;
    }

    .clear_fix:after,
    .clear_fix:before {
        content: " ";
        display: table;
    }

    .clear_fix:after {
        clear: both;
    }

    table {
        margin-right: 10px;
        width: 100%;
        height: 50%;
        border-collapse: collapse;
    }

    th, td {
        padding: 6px;
        border: #0b3d3b 2px solid;
        color: #0b3d3b;
    }

    tr:nth-child(even) {
        background-color: #c4d5d1;
    }

    .table-container {
        padding: 10px;
    }

    #search_part {
        margin-right: 30%;
        margin-top: 5%;
        text-align: center;
        width: 90%;
    }

    .input_box {
        margin-top: 5px;
        padding: 6px 20px;
        width: 48%;
        border-radius: 20px;
        border: #0b3d3b solid 1px;
    }

    #search_btn {
        background-color: #309894;
        border-radius: 20px;
        border: #0b3d3b 1px solid;
        padding: 6px 20px;
        margin: 0;
        font-weight: bold;
    }

    #add_btn {
        padding-right: 15px;
        padding-left: 15px;
    }

    .btn {
        background-color: #309894;
        border-radius: 50px;
        border: none;
        padding: 3px 10px;
    }
</style>
<div class="clear_fix bodyContent">
    <aside>
    </aside>
    <section>
        <table class="table-container">
            <tr>
                <th>rowNumber</th>
                <th>Title</th>
                <th>ISBN</th>
                <th colspan="2">
                    <button type="submit" id="add_btn" class="btn">+Add book</button>
                </th>
            </tr>
            <%
                int counter = (int) request.getAttribute("counter");
                List<Book> bookList = (List<Book>) request.getAttribute("bookList");
                for (Book book : bookList) {
            %>

            <tr>
                <td><%=counter++%>
                </td>
                <td><%=book.getTitle()%>
                </td>
                <td><%=book.getIsbn()%>
                </td>
                <td>
                    <button type="submit" class="btn">detail</button>
                </td>
                <td>
                    <button type="submit" class="btn">edit</button>
                </td>
            </tr>
            <%}%>
        </table>
    </section>
</div>