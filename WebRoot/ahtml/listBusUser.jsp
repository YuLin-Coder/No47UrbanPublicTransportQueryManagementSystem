<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${ctx}/sys/query${actionclass1}User.action" method="post">
    		<input type="hidden" name="pageCurrent" value="0">
        <div class="bjui-searchBar">
            <label>车牌号：</label><input type="text" value="" id="${actionclass1}_pai" name="s_pai" class="form-control" size="10">&nbsp;
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
                <th >车牌信息</th>
                <th >车型</th>
                <th >荷载人数</th>
                <th >是否空调车</th>
                <th >投诉电话</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${SESSION_PAGE.list}" var="item">
            <tr data-id="${item.id }">
               <td>${item.pai}</td>
               <td>${item.type}</td>
               <td>${item.wei}</td>
               <td>${item.air}</td>
               <td>${item.tel}</td>
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