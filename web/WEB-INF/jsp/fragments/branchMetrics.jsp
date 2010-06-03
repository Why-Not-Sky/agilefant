<%@ include file="/WEB-INF/jsp/inc/_taglibs.jsp" %>

<h4>Branch metrics</h4>


<table>
  <tr>
    <td>Leaf stories</td>
    <td><span style="color: green;" title="Done leaf points">${branchMetrics.doneLeafPoints}</span> / <span title="Total leaf points">${branchMetrics.leafPoints}</span></td>
    <td style="color: #666;">(${fn:substringBefore(branchMetrics.doneLeafPoints / branchMetrics.leafPoints * 100, '.')}%)</td>
  </tr>
  <tr>
    <td>Estimated progress</td>
    <td><span style="color: green;" title="Estimated done points">${branchMetrics.estimatedDonePoints}</span> / <span title="Total estimated points">${branchMetrics.estimatedPoints}</span></td>
    <td style="color: #666;">(${fn:substringBefore(branchMetrics.estimatedDonePoints / branchMetrics.estimatedPoints * 100, '.')}%)</td>
  </tr>
  <tr>
    <td colspan="2">
      <a href="#" class="quickHelpLink" onclick="HelpUtils.openHelpPopup(this,'Estimated Progress','static/html/help/branchMetricsPopup.html');return false;">
        What is this?
      </a>
    </td>
  </tr>
</table>