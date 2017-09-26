<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
function appliBonusDelete(projectId) {
	if(confirm("您确定要删除该条体制信息吗？")) {
		window.location="appliBonus?action=delete&projectId="+projectId;
	}
}

	$(document).ready(function() {
		$("ul li:eq(3)").addClass("active");
	});
</script>

<div class="data_list">		
		<div class="data_list_title">
			 <h4 style="color:black">项目奖金发放（体制信息）</h4>
		</div>
		<font size="4" face="Microsoft YaHei"> 无锡中心（项目2）-->该项目奖金(总额参考) ： 46880 </font>	
		<form name="myForm" class="form-search" method="post" action="charger/blank_2.jsp">
			<button class="btn btn-success" type="button" style="margin-left: 850px;" onclick="javascript:window.location='tiZhiSave.jsp'">添加体制信息</button>
		</form>
		
		<div>
			<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>类别</th>
					<th>工号</th>
					<th>姓名</th>
					<th>职级</th>
					<th>发放日期</th>
					<th width="20%">奖金金额</th>
					<th width="30%">操作</th>
				</tr>
			</thead>
			<tbody>			
					<tr>
						<td>已发放</td>
						<td>SW010011601</td>
						<td>小周</td>
						<td>PM</td>
						<td>2016/4/25</td>
						<td>6000
						&nbsp;&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='moneySave.jsp'">调整分配金额</button></td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="window.open(test.jsp)">设置等级</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button">删除</button>
						</td>
					</tr>
					<tr>
						<td>已发放</td>
						<td>SW010011602</td>
						<td>小林</td>
						<td>PG</td>
						<td>2016/4/25</td>
						<td>3000
							&nbsp;&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='moneySave.jsp'">调整分配金额</button></td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="window.open(test.jsp)">设置等级</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button">删除</button>
						</td>
					</tr>
			</tbody>
			</table>
			项目奖金已发放：9000
		</div>
		<br><br>
		<div>
			<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>类别</th>
					<th>工号</th>
					<th>姓名</th>
					<th>职级</th>
					<th>申请日期</th>
					<th width="20%">奖金金额</th>
					<th width="30%">操作</th>
				</tr>
			</thead>
			<tbody>			
					<tr>
						<td>未发放</td>
						<td>SW010011603</td>
						<td>小方</td>
						<td>PM</td>
						<td>2016/4/25</td>
						<td>2000
						&nbsp;&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='moneySave.jsp'">调整分配金额</button></td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="window.open(test.jsp)">设置等级</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button">删除</button>
						</td>
					</tr>
					<tr>
						<td>未发放</td>
						<td>SW010011604</td>
						<td>小李</td>
						<td>SE</td>
						<td>2016/4/25</td>
						<td>1000
						&nbsp;&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='moneySave.jsp'">调整分配金额</button></td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="window.open(test.jsp)">设置等级</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button">删除</button>
						</td>
					</tr>
					<tr>
						<td>未发放</td>
						<td>SW010011605</td>
						<td>小王</td>
						<td>PG</td>
						<td>2016/4/25</td>
						<td>3000
						&nbsp;&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='moneySave.jsp'">调整分配金额</button></td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="window.open(test.jsp)">设置等级</button>
							&nbsp;
							<button class="btn btn-mini btn-danger" type="button">删除</button>
						</td>
					</tr>
			</tbody>
			</table>
			项目奖金未发放：6000
	</div>
</div>
