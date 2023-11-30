<c:if test="${not empty mensagem }">
	<div class="alert alert-success">${mensagem}</div>
</c:if>

<c:if test="${not empty erro }">
	<div class="alert alert-danger">${erro}</div>
</c:if>