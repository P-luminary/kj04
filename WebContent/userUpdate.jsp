<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common.jsp" %>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="use?method=updateUser" method="post">
            <input type="hidden" name="id" value="${user.id }"/>
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="realname">真实姓名：</label>
                    <input type="text" name="realname" id="realname" value="${user.realname }"/>
                    <span >*</span>
                </div>

                <div>
                    <label>用户性别：</label>      
                    	<c:forEach items="${sexs }" var="ss">
                        <input type="radio" name="sex" value="${ss.id }" 
                        
                        <c:if test="${ss.id == user.sex }">
                        checked="checked"
                        </c:if>
                        
                        />${ss.name }
                        </c:forEach>
                </div>
                
                <div>
                    <label for="birthday">出生日期：</label>
                    <input value="${user.birthday}" name="birthday" value="" readonly="readonly" onClick="SelectDate(this,'yyyy-MM-dd')"/>                    <span >*</span>
                </div>
                <div>
                    <label for="tel">用户电话：</label>
                    <input type="text" name="tel" id="tel" value="${user.tel }"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                    <input type="text" name="address" id="address" value="${user.address }"/>
                </div>
                <div>
                    <label >用户类别：</label>
                     <input type="radio" name="userlei" value="0"/>学员
                    <input type="radio" name="userlei" value="1"/>老师
                    <input type="radio" name="userlei" value="2"/>管理员

                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存" />
                    <input type="button" value="返回" />
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>