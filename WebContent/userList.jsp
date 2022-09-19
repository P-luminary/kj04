<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="common.jsp" %>
     <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
            <form action="use?method=getAll" method="post" id="myForm">
                <span>用户名：</span>
                <input type="text" name="username" value="${username }" placeholder="请输入账号"/>
                <input type="submit" value="查询"/>
                <a href="use?method=preAdd">添加用户</a>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户账号</th>
                    <th width="20%">真实姓名</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户类型</th>
                    <th width="10%">封号状态</th>
                    <th width="10%">头像</th>
                    <th width="20%">操作</th>
                </tr>
               
               <c:forEach items="${users }" var="us">
				 <tr>
                    <td>${us.username }</td>
                    <td>${us.realname }</td>
                    <td>
                    <c:if test="${us.sex == '1' }">
                    	男
                    </c:if>
                    <c:if test="${us.sex == '2' }">
                    	女
                    </c:if>
                    </td>
                    <td>${us.birthday }</td>
                    <td>${us.tel }</td>
                    <td>
                    <c:if test="${us.type == '1'}">
                    	學員
                    </c:if>
                    <c:if test="${us.type == '2' }">
                    	老師
                    </c:if>
                    <c:if test="${us.type == '3' }">
                    	管理員
                    </c:if>
                   </td>
                   
                   <td>
                   <c:if test="${us.if_valid == '1' }">
                    	<a href = "use?method=updateStatus&status=0&id=${us.id}">封号</a>
                    </c:if>
                    <c:if test="${us.if_valid == '0' }">
                    	<a href = "use?method=updateStatus&status=1&id=${us.id}">解封</a>
                    </c:if>
                   </td>
                   
                   <td>
                   	<img src="${us.photo }" width="50"/>
                   	<a href="${us.photo }">查看头像</a>
                   </td>
                   
                    <td>
                        <a href="userView.html"><img src="img/read.png" alt="查看" title="查看"/></a>
                        <a href="use?method=preUpdate&id=${us.id }"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                        <a onclick="return confirm('您确定要删除吗？')" href="use?method=delUser&id=${us.id}" class="removeUser"><img src="img/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
   			</c:forEach> 
   			<tr>
				<td colspan="8"> <!-- 跨8列 -->
				 <%@ include file="page.jsp" %>
				</td>
				</tr>
            </table>
        </div>
    </section>
</form>
<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

    <footer class="footer">
    </footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>