<!DOCTYPE html>
<html>
<head>
   <jsp:include page="headers.jsp" />
    </head>
    </div>
<jsp:include page="navigation.jsp" />
        <div style="text-align:center">
        <p style = "color:#3c261e; font-size:20px;">Developers Page </p></div>
<div class="container">
    <div class="row">
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/developers/new" type="button" class="btn btn-primary">New</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Age</th>
                <th scope="col">Gender</th>
                <th scope="col">Salary</th>
                <th scope="col">Action</th>
             </tr>
            </thead>
            <tbody>
            <%
            Object[] developers = (Object[]) request.getAttribute("developers");
                for(Object objDeveloper : developers){
                model.Developer developer = (model.Developer) objDeveloper;
                %>

                <tr>
                    <td><%= developer.getId() %></td>
                    <td><%= developer.getName()  %></td>
                    <td><%= developer.getAge()  %></td>
                    <td><%= developer.getGender() %></td>
                    <td><%= developer.getSalary()  %></td>
                    <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group mr-2" role="group" aria-label="First group">
                                    <a href="/developers/<%= developer.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                    <a href="/developers?deleteId=<%= developer.getId() %>"type="button" class="btn btn-danger">Remove</a>
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