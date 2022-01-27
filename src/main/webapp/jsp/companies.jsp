<!DOCTYPE html>
<html>
<head>
    <title>Companies page</title>
   <jsp:include page="headers.jsp" />
    </head>

<jsp:include page="navigation.jsp" />
        <div style="text-align:center">
        <p style = "color:#3c261e; font-size:20px;">Companies Page </p></div>
<div class="container">
    <div class="row">
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/companies/new" type="button" class="btn btn-primary">New</a>
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
            Object[] companies = (Object[]) request.getAttribute("companies");
                for(Object objCompany : companies){
                model.Company company = (model.Company) objCompany;
                %>

                <tr>
                    <td><%= company.getId() %></td>
                    <td><%= company.getName()  %></td>
                    <td><%= company.getDescription()  %></td>
                    <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group mr-2" role="group" aria-label="First group">
                                    <a href="/companies/<%= company.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                    <a href="/companies?deleteId=<%= company.getId() %>"type="button" class="btn btn-danger">Remove</a>
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