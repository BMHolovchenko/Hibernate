<!DOCTYPE html>
<html>
<head>
    <title>Customers page</title>
   <jsp:include page="headers.jsp" />
    </head>

<jsp:include page="navigation.jsp" />
        <div style="text-align:center">
        <p style = "color:#3c261e; font-size:20px;">Customers Page </p></div>
<div class="container">
    <div class="row">
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/customers/new" type="button" class="btn btn-primary">New</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Action</th>
             </tr>
            </thead>
            <tbody>
            <%
            Object[] customers = (Object[]) request.getAttribute("customers");
                for(Object objCustomer : customers){
                model.Customer customer = (model.Customer) objCustomer;
                %>

                <tr>
                    <td><%= customer.getId() %></td>
                    <td><%= customer.getName()  %></td>
                    <td><%= customer.getDescription()  %></td>
                    <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group mr-2" role="group" aria-label="First group">
                                    <a href="/customers/<%= customer.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                    <a href="/customers?deleteId=<%= customer.getId() %>"type="button" class="btn btn-danger">Remove</a>
                                </div>

                            </div>
                        </td>
                 </tr>

            <% } %>

            </tbody>
        </table>
    </div>
</div>

</body>
</html>