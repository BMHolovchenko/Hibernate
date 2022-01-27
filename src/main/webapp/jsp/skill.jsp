<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="headers.jsp"/>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<% model.Skill skill = (model.Skill) request.getAttribute("skill"); %>
<div class="container">
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/skills" type="button" class="btn btn-success">Back to skills</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="text" disabled class="form-control"
                   value="<%= skill.getId() == null ? "" : skill.getId() %>"
                   id="id" placeholder="Id">
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control"
                   value="<%= skill.getName() == null ? "" : skill.getName() %>"
                   id="name" placeholder="Skill name">
        </div>
        <div class="mb-3">
            <label for="level" class="form-label">Level</label>
            <input type="text" class="form-control"
                   value="<%= skill.getLevel() == null ? "" : skill.getLevel() %>"
                   id="level" placeholder="Skill level">
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
    let level = document.getElementById('level');

    function save() {
     let body = {
     <% if(skill.getId() != null) {%>
         id: id.value,
      <% } %>
         name: name.value,
         level: level.value,
      }
      <% if(skill.getId() == null) {%>
         let url = '/skills';
         let method = 'POST';
      <% } else { %>
         let url = '/skills/<%= skill.getId() %>';
         let method = 'PUT';
      <% } %>
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
        .then( _ => {
            window.location.href = '/skills';
        }
        );
    }
</script>
</body>
</html>