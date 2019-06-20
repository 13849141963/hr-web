<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width" />
    <title>报销单明细</title>
    <style type="text/css">
		body {
			font-family: SimSun;
		} 
        .kuang td {
            padding-left: 6px;
            height: 34px;
            font-size: 14px;
            /*border-right: 1px solid #c7c7c7;
            border-bottom: 1px solid #c7c7c7;*/
            border: 1px solid #c7c7c7;
        }

        .kuang1 td {
            text-align: center;
            height: 34px;
            font-size: 14px;
            border-left: 1px solid #c7c7c7;
            border-top: 1px solid #c7c7c7;
            width:50px;
        }

        .kuang2 {
            border-left: 1px solid #c7c7c7;
            border-top: 1px solid #c7c7c7;
            border-right: 1px solid #c7c7c7;
        }
        .kuang3 {
            border-bottom: 1px solid #c7c7c7;
            
        }

            .kuang th, kuang1 th {
                font: 12px/24px '微软雅黑';
                color: #000;
            }

        	.xiaobiao {
            color: #0099cb;
        }

        
    </style>
</head>
<body>
	<#list datas as billsDetail>
		<div>
        <table border="0" width="660" align="center" class="kuang"  style="table-layout:fixed">
                    <tr>
                        <td class="kuang1">单号:</td>
                        <td class="kuang1">${billsDetail.billNo!}</td>
                        <td class="kuang1">报销人员:</td>
                        <td class="kuang1">${billsDetail.applyer!} </td>
                        <td class="kuang1">报销人姓名:</td>
                        <td class="kuang2">${billsDetail.familyName!}</td>
                    </tr>
                    <tr>
                        <td class="kuang1">身份证号:</td>
                        <td class="kuang1">${billsDetail.familyCardNo!}</td>
                        <td class="kuang1">就诊医院:</td>
                        <td class="kuang1"  style="word-wrap:break-word">${billsDetail.placeHospital!}</td>
                        <td class="kuang1">合理金额:</td>
                        <td class="kuang2">${billsDetail.payMoney!}</td>
                    </tr>
                    <tr>
                        <td class="kuang1">费用类型:</td>
                        <td class="kuang1">${billsDetail.chargeSort!}</td>
                        <td class="kuang1">审核日期:</td>
                        <td class="kuang1">${billsDetail.approveDate!}</td>
                        <td class="kuang1">审单大夫:</td>
                        <td class="kuang2">${billsDetail.chargeSort!}</td>
                    </tr>
                    <tr>
                        <td colspan="1" class="kuang1">单据统计:</td>
                        <td colspan="5" class="kuang2">${billsDetail.billStatistics!}</td>
                    </tr>
                    <tr>
                        <td colspan="1" class="kuang1">扣除明细:</td>
                        <td colspan="5" class="kuang2">
                             ${billsDetail.detail!}                       
                        </td>
                    </tr>            
                    <tr><td colspan="6"></td></tr>
        </table>
	</div>
	</#list>
    
</body>
</html>
