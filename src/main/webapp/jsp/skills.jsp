<!DOCTYPE html>
<html>
<head>
    <title>Skills page</title>
   <jsp:include page="headers.jsp" />
    </head>

<jsp:include page="navigation.jsp" />
        <div style="text-align:center">
        <p style = "color:#3c261e; font-size:20px;">Skills Page </p></div>
<div class="container">
    <div class="row">
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/skills/new" type="button" class="btn btn-primary">New</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Level</th>
                <th scope="col">Action</th>
             </tr>
            </thead>
            <tbody>
            <%
            Object[] skills = (Object[]) request.getAttribute("skills");
                for(Object objSkill : skills){
                model.Skill skill = (model.Skill) objSkill;
                %>

                <tr>
                    <td><%= skill.getId() %></td>
                    <td><%= skill.getName()  %></td>
                    <td><%= skill.getLevel()  %></td>
                    <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group mr-2" role="group" aria-label="First group">
                                    <a href="/skills/<%= skill.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                    <a href="/skills?deleteId=<%= skill.getId() %>"type="button" class="btn btn-danger">Remove</a>
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