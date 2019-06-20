<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>报销详情</title>
	<style type="text/css">
		body { 
		font-family: SimSun;
		} 
		.kuang td {
            padding-left: 6px;
            height: 34px;
            font-size: 14px;
            border-right: 1px solid #c7c7c7;
            border-bottom: 1px solid #c7c7c7;
        }
		.kuang1 td {
            text-align: center;
            height: 34px;
            font-size: 14px;
            border-right: 1px solid #c7c7c7;
            border-bottom: 1px solid #c7c7c7;
        }
		.kuang, .kuang1 {
            border-left: 1px solid #c7c7c7;
            border-top: 1px solid #c7c7c7;
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
	<#list datas as billsSummary>
	 <table border="0" width="650" align="center">
        <tbody>
            <tr>
                <th colspan="4" style="text-align:center">FESCO员工医疗保障索赔申请单</th>
            </tr>
            <tr>
                <td class="xiaobiao">(以下内容由FESCO工作人员填写)</td>
            </tr>
            <tr>
                <td colspan="2" align="left">唯一号：${billsSummary.uniqNo!}</td>
                <td colspan="2" align="left">报销单编号：${billsSummary.billNo!}</td>
            </tr>
        </tbody>
    </table>
	<table width="650" align="center" cellpadding="0" cellspacing="0" class="kuang">
        <tbody>
            <tr>
                <td>员工姓名:${billsSummary.name!}</td>
                <td colspan="2">业务部:${billsSummary.busiDepName!}</td>
            </tr>
            <tr>
                <td colspan="3"><span style="line-height:25px;font-weight:600">医保范围内扣除金额:${billsSummary.detail!}</span>
                        <span style="font-size:14px"></span>
                        <span style="line-height:25px;font-weight:600"></span>   
                         <span style="font-size:14px"></span>
                         <span style="line-height:25px;font-weight:600"></span> 
                         <span style="font-size:14px"></span>
                         <span style="line-height:25px;font-weight:600"></span>
                         <span style="font-size:14px"></span>
                         <span style="line-height:25px;font-weight:600"></span>                  
                         <span style="font-size:14px"></span>
                         <span style="line-height:25px;font-weight:600"></span>              
                         <span style="font-size:14px"></span>
                         <span style="line-height:25px;font-weight:600"></span>
                             
                </td>
            </tr>
            <tr>
                <td colspan="3">合理金额:${billsSummary.payMoney!}</td>
            </tr>
            <tr>
                <td colspan="3">特殊合理金额:${billsSummary.specialPayMoney!}</td>
            </tr>
            <tr>
                <td colspan="2">审单日期:${billsSummary.approveDate!}</td>
                <td>大夫签字:${billsSummary.approver!}</td>
            </tr>
            <tr>
                <td colspan="3">备注:</td>
            </tr>
        </tbody>
    </table>
	<p class="xuxian"></p>
	 <table border="0" width="650" align="center">
        <tbody>
            <tr>
                <th colspan="4" style="text-align:center">FESCO员工医疗保障索赔申请单</th>
            </tr>
            <tr>
                <td class="xiaobiao">(以下内容由员工填写)</td>
            </tr>
        </tbody>
    </table>
	<div>
        <table width="650" align="center" cellpadding="0" cellspacing="0" class="kuang">
            <tbody>
                <tr>
                    <td>唯一号：${billsSummary.uniqNo!}</td>
                    <td>姓名：${billsSummary.name!}</td>
                    <td colspan="2">业务部：${billsSummary.busiDepName!}</td>
                </tr>
                <tr>
                    <td colspan="4">身份证号：${billsSummary.cardNo!}</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <table width="650">
                            <tbody>
                                <tr>
                                    <td colspan="3">报销人姓名：${billsSummary.familyName!}</td>
                                    <td colspan="3" style="border-right:0px solid">报销人员：${billsSummary.applyer!}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="border-bottom:0px solid;border-right:0px solid">身份证号:${billsSummary.familyCardNo!}</td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">就诊医院：${billsSummary.placeHospital!}</td>
                    <td colspan="2">费用类型：${billsSummary.chargeSort!}</td>         
                </tr>
                <tr>               
                    <td colspan="4">就诊日期：${billsSummary.reimbTime!}</td>
                </tr>
                <tr>
                    <td colspan="2">单据统计：${billsSummary.billStatistics!}</td>
                    <td colspan="2">报销类型：${billsSummary.applySort!}</td>
                </tr>               
                <tr>
                    <td colspan="4" style="line-height:150%">开户行：${billsSummary.bankName!}<br />账号：${billsSummary.bankCard!}</td>
                </tr>
                <tr style="line-height:150%">
                    <td>填报日期：${billsSummary.fillTime!} </td>
                    <td colspan="4">本人确认所提交单据真实。<br />本人已阅读“温馨提示”，知悉并理解报销事项。<br />员工签名：${billsSummary.name!} 联系电话：${billsSummary.tel!}</td>
                </tr>
            </tbody>
        </table>
    </div>
	</#list>
</body>
</html>