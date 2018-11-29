<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/common/meta.jsp" flush="true"></jsp:include>
	<title>中移动流量采集监测系统</title>
	<link rel="stylesheet" type="text/css" href="${cssPath}/style-main.css" />
</head>

<body>
	<!-- #BeginLibraryItem "/Library/header.lbi" -->
	<jsp:include page="/WEB-INF/common/modal.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
	<!-- #EndLibraryItem -->
	<div class="main">
		<jsp:include page="/WEB-INF/common/sidel.jsp" flush="true"></jsp:include>
		<!-- #EndLibraryItem -->
		<div class="sider">
			<div class="con">
				<div class="frame">
					<div class="title">
						<i></i> > <span class="blue">CMNET流量</span> / <span class="blue">总览概况</span>
						/ <span>流量总览概况</span>
						<button class="fr drop_down">查询</button>
						<button class="fr">导出</button>
					</div>
					<div class="time_search">
						<a href="">最近1小时</a> <a href="">最近24小时</a> <a href="">最近7天</a> <a
							href="">最近30天</a>
					</div>
					<div class="traffic_chart">
						<div class="table-title">
							<a href="">全网流量趋势分布</a>
							<div id="chart_a" style="width: 100%; height: 300px;"></div>
						</div>
						<div class="table-title">
							<a href="">机房流量</a>
							<div id="chart_b" style="width: 100%; height: 300px;"></div>
						</div>
						<div class="table-title">
							<a href="">TOP5业务流量</a>
							<div id="chart_d" style="width: 100%; height: 300px;"></div>
						</div>
						<div class="table-title">
							<a href="">TOP5地域来源流量</a>
							<div id="chart_f" style="width: 100%; height: 300px;"></div>
						</div>
						<div class="table-title">
							<a href="">运营商流量占比</a>
							<div id="chart_e" style="width: 100%; height: 300px;"></div>
						</div>
						<div class="table-title">
							<a href="">TOP5应用层协议流量t</a>
							<div id="chart_g" style="width: 100%; height: 300px;"></div>
						</div>
						<div class="table-title">
							<a href="">TOP5目的IP地址流量</a>
							<div id="chart_h" style="width: 100%; height: 300px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>