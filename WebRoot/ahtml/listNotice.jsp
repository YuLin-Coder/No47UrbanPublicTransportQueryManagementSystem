<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${ctx}/sys/query${actionclass1}.action" method="post">
    		<input type="hidden" name="pageCurrent" value="0">
        <div class="bjui-searchBar">
            <label>车牌号：</label><input type="text" value="" id="${actionclass1}_pai" name="s_pai" class="form-control" size="10">&nbsp;
<!--             <label>:</label> -->
<!--             <select name="type" data-toggle="selectpicker"> -->
<!--                 <option value="">全部</option> -->
<!--             </select>&nbsp; -->
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <div class="pull-right">
                <div class="btn-group">
                	<button type="button" class="btn-blue" data-url="${ctx}/sys/add2${actionclass1}.action" data-toggle="navtab" data-options="{reloadWarn:''}" data-id="baseAdd" 
                		data-icon="plus" title="添加记录">添加${actionname1}</button>&nbsp;
                	<button type="button" class="btn-blue" data-url="${ctx}/sys/delete${actionclass1}.action" data-toggle="doajaxchecked" 
                		data-confirm-msg="确定要删除选中项吗？" data-idname="ids" data-group="ids" data-icon="remove" title="可以在控制台(network)查看被删除ID">批量删除</button>&nbsp;
                </div>
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-nowrap="true">
        <thead>
            <tr>
                <th >标题</th>
                <th >内容</th>
                <th >时间</th>
                <th width="26"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${SESSION_PAGE.list}" var="item">
            <tr data-id="${item.id }">
               <td>${item.title}</td>
               <td>${item.content}</td>
               <td>${item.addDate}</td>
                <td><input type="checkbox" name="ids" data-toggle="icheck" value="${item.id }"></td>
                <td>
                    <a href="${ctx}/sys/get${actionclass1}.action?uid=${item.id}" class="btn btn-green" data-toggle="navtab" data-options="{reloadWarn:''}" data-id="baseAdd" data-reload-warn="本页已有打开的内容，确定将刷新本页内容，是否继续？" data-title="更新${actionname1}">编辑</a>
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