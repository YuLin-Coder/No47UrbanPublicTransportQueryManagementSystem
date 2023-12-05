<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${ctx}/sys/query${actionclass1}.action" method="post">
    		<input type="hidden" name="pageCurrent" value="0">
        <div class="bjui-searchBar">
            <div class="pull-right">
                <div class="btn-group">
                	<button type="button" class="btn-blue" data-url="${ctx}/sys/add2${actionclass1}.action" data-toggle="doajax" 
                		 data-icon="add" title="可以在控制台(network)查看被删除ID">备份数据库</button>&nbsp;
                </div>
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
            <tr>
                <th width="100">备份文件</th>
				<th width="100">备份时间</th>

				<th width="70">还原</th>
				<th width="70">删除</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${SESSION_PAGE.list}" var="item">
            <tr data-id="${item.id }">
              <td>${item.filePath}</td>
				<td>${item.addDate}</td>
                <td>
                    <a href="${ctx}/sys/get${actionclass1}.action?uid=${item.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要还原数据库吗？">还原</a>
                </td>
                <td>
                    <a href="${ctx}/sys/delete${actionclass1}.action?ids=${item.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该行信息吗？">删</a>
                </td>
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