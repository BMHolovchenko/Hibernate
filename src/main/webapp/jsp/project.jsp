<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="headers.jsp"/>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<% model.Project project = (model.Project) request.getAttribute("project"); %>
<div class="container">
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/projects" type="button" class="btn btn-success">Back to projects</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="text" disabled class="form-control"
                   value="<%= project.getId() == null ? "" : project.getId() %>"
                   id="id" placeholder="Id">
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control"
                   value="<%= project.getName() == null ? "" : project.getName() %>"
                   id="name" placeholder="Project name">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control"
                   value="<%= project.getDescription() == null ? "" : project.getDescription() %>"
                   id="description" placeholder="Project description">
        </div>
        <div class="mb-3">
            <label for="date" class="form-label">Date</label>
            <input type="text" class="form-control"
                   value="<%= project.getDate() == null ? "" : project.getDate() %>"
                   id="date" placeholder="Project date">
        </div>
        <div class="mb-3">
            <label for="cost" class="form-label">Cost</label>
            <input type="text" class="form-control"
                   value="<%= project.getCost() == null ? "" : project.getCost() %>"
                   id="cost" placeholder="Project cost">
        </div>

    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <button onclick="save()" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>
<script>
    let id = document.getElementById('id');
    let name = document.getElementById('name');
    let description = document.getElementById('description');
    let date = document.getElementById('date');
    let cost = document.getElementById('cost');

    function save() {
     let body = {
     <% if(project.getId() != null) {%>
         id: id.value,
      <% } %>
         name: name.value,
         description: description.value,
         date: date.value,
         cost: cost.value,
      }
      <% if(project.getId() == null) {%>
         let url = '/projects';
         let method = 'POST';
      <% } else { %>
         let url = '/projects/<%= project.getId() %>';
         let method = 'PUT';
      <% } %>
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
        .then( _ => {
            window.location.href = '/projects';
        }
        );
    }
</script>
</body>
</html>