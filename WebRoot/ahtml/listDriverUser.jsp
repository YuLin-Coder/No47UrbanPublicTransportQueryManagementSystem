<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${ctx}/sys/query${actionclass1}User.action" method="post">
    		<input type="hidden" name="pageCurrent" value="0">
        <div class="bjui-searchBar">
            <label>姓名：</label><input type="text" value="" id="${actionclass1}_name" name="s_name" class="form-control" size="10">&nbsp;
            <label>工号：</label><input type="text" value="" id="${actionclass1}_cardno" name="s_cardno" class="form-control" size="10">&nbsp;
<!--             <label>:</label> -->
<!--             <select name="type" data-toggle="selectpicker"> -->
<!--                 <option value="">全部</option> -->
<!--             </select>&nbsp; -->
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
            <tr>
                <th >姓名</th>
                <th >工号</th>
                <th >驾龄</th>
                <th >身体状况</th>
                <th >性别</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${SESSION_PAGE.list}" var="item">
            <tr data-id="${item.id }">
               <td>${item.name}</td>
               <td>${item.cardno}</td>
               <td>${item.driverage}</td>
               <td>${item.health}</td>
                <td>${item.gender}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter">
    <div class="pages">
        <span>&nbsp;共 ${SESSION_PAGE.totalNumber} 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="${SESSION_PAGE.totalNumber}" data-page-size="${SESSION_PAGE.itemsPerPage}" data-page-current="${SESSION_PAGE.currentPageNumber}">
    </div>
    </div>
<script type="text/javascript">
<c:forEach items="${textmap }" var="keyitem">
$("#${actionclass1}_${keyitem.key}").val("${keyitem.value}");
</c:forEach>
</script>