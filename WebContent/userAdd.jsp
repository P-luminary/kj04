<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "common.jsp" %>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="use?method=addUser" method="post" enctype="multipart/form-data"> <!-- 编码方式IO流 -->      
			<!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="userId">用户账号：</label>
                    <input type="text" name="username" id="userId"/>
                    <span>*请输入用户账号，且不能重复</span>
                </div>
                <div>
                    <label for="realname">真实姓名：</label>
                    <input type="text" name="userName" id="userName"/>
                    <span >*请输入用真实姓名</span>
                </div>
                <div>
                    <label for="password">用户密码：</label>
                    <input type="text" name="userpassword" id="userpassword"/>
                    <span>*密码长度必须大于6位小于20位</span>

                </div>
                <div>
                    <label for="userRemi">确认密码：</label>
                    <input type="text" name="userRemi" id="userRemi"/>
                    <span>*请输入确认密码</span>
                </div>
                <div>
                    8，
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <input type="text" name="birthday" value="" readonly="readonly" onClick="SelectDate(this,'yyyy-MM-dd')"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="tel">用户电话：</label>
                    <input type="text" name="tel" id="userphone"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                    <input type="text" name=address" id="address"/>
                </div>
                <div>
                    <label >用户类别：</label>
                    <input type="radio" name="type" value="1"/>学员
                    <input type="radio" name="type" value="2"/>老师
                    <input type="radio" name="type" value="3"/>管理员
                </div>
                <div>
                    <label>上传头像：</label> <!-- 10行 必须要post方式 和 enctype -->
                    <input type="file" name="type" value="1"/>
                 </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存" /> <!-- 保存进数据库 第10行 -->
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