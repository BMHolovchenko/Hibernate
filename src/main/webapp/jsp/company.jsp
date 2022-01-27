<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="headers.jsp"/>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<% model.Company company = (model.Company) request.getAttribute("company"); %>
<div class="container">
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/companies" type="button" class="btn btn-success">Back to companies</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="text" disabled class="form-control"
                   value="<%= company.getId() == null ? "" : company.getId() %>"
                   id="id" placeholder="Id">
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control"
                   value="<%= company.getName() == null ? "" : company.getName() %>"
                   id="name" placeholder="Company name">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control"
                   value="<%= company.getDescription() == null ? "" : company.getDescription() %>"
                   id="description" placeholder="Company description">
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

    function save() {
     let body = {
     <% if(company.getId() != null) {%>
         id: id.value,
      <% } %>
         name: name.value,
         description: description.value,
      }
      <% if(company.getId() == null) {%>
         let url = '/companies';
         let method = 'POST';
      <% } else { %>
         let url = '/companies/<%= company.getId() %>';
         let method = 'PUT';
      <% } %>
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
        .then( _ => {
            window.location.href = '/companies';
        }
        );
    }
</script>
</body>
</html>