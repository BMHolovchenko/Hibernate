<!DOCTYPE html>
<html>
<head>
    <title>Projects page</title>
   <jsp:include page="headers.jsp" />
    </head>

<jsp:include page="navigation.jsp" />
        <div style="text-align:center">
        <p style = "color:#3c261e; font-size:20px;">Projects Page </p></div>
<div class="container">
    <div class="row">
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/projects/new" type="button" class="btn btn-primary">New</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Date</th>
                <th scope="col">Cost</th>
                <th scope="col">Action</th>
             </tr>
            </thead>
            <tbody>
            <%
            Object[] projects = (Object[]) request.getAttribute("projects");
                for(Object objProject : projects){
                model.Project project = (model.Project) objProject;
                %>

                <tr>
                    <td><%= project.getId() %></td>
                    <td><%= project.getName()  %></td>
                    <td><%= project.getDescription()  %></td>
                    <td><%= project.getDate()  %></td>
                    <td><%= project.getCost()  %></td>
                    <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group mr-2" role="group" aria-label="First group">
                                    <a href="/projects/<%= project.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                    <a href="/projects?deleteId=<%= project.getId() %>"type="button" class="btn btn-danger">Remove</a>
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