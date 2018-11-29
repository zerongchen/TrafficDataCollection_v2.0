package com.aotain.common.util;

import java.util.List;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

import com.aotain.tdc.constant.KPIParamType;
import com.aotain.tdc.constant.KPIType;
import com.aotain.tdc.dto.common.KPIBean;
import com.aotain.tdc.dto.common.SelectorBean;

/**
 * @ClassName: KPIUtil
 * @author 凯哥
 * @date 2016年11月8日 下午3:31:52
 * @Description: 中移质量指标计算工具类
 * @KeyWord: JEXL
 *
 */
public class KPIUtil {

	private static final JexlEngine jexl = new JexlBuilder().cache(512).strict(true).silent(false).create();

	public static Float computeScore(KPIBean kpiBean, KPIType kpiType, List<SelectorBean> kpiConfigList) {
		try {
			float x = 0.0f;
			float totalScore = 0.0f;
			float successPercent = 0.0f, respDelay = 0.0f, serverDelay = 0.0f, clientDelay = 0.0f, timeoutPercent = 0.0f, upRetransPercent = 0.0f, downRetransPercent = 0.0f;
			float successPercentWeight = 0.0f, respDelayWeight = 0.0f, serverDelayWeight = 0.0f, clientDelayWeight = 0.0f, timeoutPercentWeight = 0.0f, upRetransPercentWeight = 0.0f, downRetransPercentWeight = 0.0f;
			String calculateTax = null;

			for (int i = 0; i < kpiConfigList.size(); i++) {
				SelectorBean kpiConfig = kpiConfigList.get(i);
				switch (determineKPIParamType(kpiConfig.getQuotaName())) {
				case SUCCESS_PERCENT:
					x = kpiBean.getSuccessPercent();
					successPercent = determineParams(kpiType, x, kpiConfig);
					successPercentWeight = kpiConfig.getQuotaWeight();
					break;
				case RESP_DELAY:
					x = kpiBean.getRespDelay();
					respDelay = determineParams(kpiType, x, kpiConfig);
					respDelayWeight = kpiConfig.getQuotaWeight();
					break;
				case SERVER_DELAY:
					x = kpiBean.getServerDelay();
					serverDelay = determineParams(kpiType, x, kpiConfig);
					serverDelayWeight = kpiConfig.getQuotaWeight();
					break;
				case CLIENT_DELAY:
					x = kpiBean.getClientDelay();
					clientDelay = determineParams(kpiType, x, kpiConfig);
					clientDelayWeight = kpiConfig.getQuotaWeight();
					break;
				case TIMEOUT_PERCENT:
					x = kpiBean.getTimeoutPercent();
					timeoutPercent = determineParams(kpiType, x, kpiConfig);
					timeoutPercentWeight = kpiConfig.getQuotaWeight();
					break;
				case UP_RETRANS_PERCENT:
					x = kpiBean.getUpRetransPercent();
					upRetransPercent = determineParams(kpiType, x, kpiConfig);
					upRetransPercentWeight = kpiConfig.getQuotaWeight();
					break;
				case DOWN_RETRANS_PERCNET:
					x = kpiBean.getDownRetransPercent();
					downRetransPercent = determineParams(kpiType, x, kpiConfig);
					downRetransPercentWeight = kpiConfig.getQuotaWeight();
					break;
				}
			}
			switch (kpiType) {
			case AREA_KPI:
				calculateTax = LocalConfig.getInstance().getAreaKpiTotalScoreExl();
				break;
			case CARRIER_KPI:
				calculateTax = LocalConfig.getInstance().getCarrierKpiTotalScoreExl();
				break;
			case ROOM_KPI:
				calculateTax = LocalConfig.getInstance().getRoomKpiTotalScoreExl();
				break;
			case PRODUCT_KPI:
				calculateTax = LocalConfig.getInstance().getProductKpiTotalScoreExl();
				break;
			case IP_KPI:
				calculateTax = LocalConfig.getInstance().getIpKpiTotalScoreExl();
				break;
			}
			JexlExpression e = jexl.createExpression(calculateTax);
			JexlContext context = new MapContext();

			context.set("successPercent", successPercent);
			context.set("respDelay", respDelay);
			context.set("serverDelay", serverDelay);
			context.set("clientDelay", clientDelay);
			context.set("timeoutPercent", timeoutPercent);
			context.set("upRetransPercent", upRetransPercent);
			context.set("downRetransPercent", downRetransPercent);

			context.set("successPercentWeight", successPercentWeight);
			context.set("respDelayWeight", respDelayWeight);
			context.set("serverDelayWeight", serverDelayWeight);
			context.set("clientDelayWeight", clientDelayWeight);
			context.set("timeoutPercentWeight", timeoutPercentWeight);
			context.set("upRetransPercentWeight", upRetransPercentWeight);
			context.set("downRetransPercentWeight", downRetransPercentWeight);
			Number result = (Number) e.evaluate(context);
			totalScore = result.floatValue();
			return totalScore;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static KPIParamType determineKPIParamType(String quotaName) {
		KPIParamType[] types = KPIParamType.values();
		for (KPIParamType type : types) {
			if (type.getType().equals(quotaName))
				return type;
		}
		return null;
	}

	private static float determineParams(KPIType kpiType, float x, SelectorBean kpiConfig) {
		float leftScore = 0.0f, leftValue = 0.0f, rightValue = 0.0f, rightScore = 0.0f;
		float score = 0.0f;
		// polar > 0 -- 正相关指标 polar < 0 --负相关指标
		float polar = kpiConfig.getQuotaFullPoint() - kpiConfig.getQuotaGoodPoint();

		if ((polar > 0 && x >= kpiConfig.getQuotaFullPoint()) || (polar < 0 && x <= kpiConfig.getQuotaFullPoint())) {
			score = 100;
		} else if ((x < kpiConfig.getQuotaFullPoint() && x >= kpiConfig.getQuotaGoodPoint())
				|| (x > kpiConfig.getQuotaFullPoint() && x <= kpiConfig.getQuotaGoodPoint())) {
			leftScore = 90;
			rightScore = 100;
			leftValue = kpiConfig.getQuotaGoodPoint();
			rightValue = kpiConfig.getQuotaFullPoint();
			score = determineScore(kpiType, x, leftScore, rightScore, leftValue, rightValue);
		} else if ((x < kpiConfig.getQuotaGoodPoint() && x >= kpiConfig.getQuotaOkPoint())
				|| (x >= kpiConfig.getQuotaGoodPoint() && x <= kpiConfig.getQuotaOkPoint())) {
			leftScore = 60;
			rightScore = 90;
			leftValue = kpiConfig.getQuotaOkPoint();
			rightValue = kpiConfig.getQuotaGoodPoint();
			score = determineScore(kpiType, x, leftScore, rightScore, leftValue, rightValue);
		} else if ((x < kpiConfig.getQuotaOkPoint() && x >= kpiConfig.getQuotaBasePoint())
				|| (x > kpiConfig.getQuotaOkPoint() && x <= kpiConfig.getQuotaBasePoint())) {
			leftScore = 0;
			rightScore = 60;
			leftValue = kpiConfig.getQuotaBasePoint();
			rightValue = kpiConfig.getQuotaOkPoint();
			score = determineScore(kpiType, x, leftScore, rightScore, leftValue, rightValue);
		} else if ((polar > 0 && x <= kpiConfig.getQuotaFullPoint()) || (polar < 0 && x >= kpiConfig.getQuotaFullPoint())) {
			score = 0;
		}
		return score;
	}

	private static float determineScore(KPIType kpiType, float x, float leftScore, float rightScore, float leftValue, float rightValue) {
		String calculateTax = null;
		switch (kpiType) {
		case AREA_KPI:
			calculateTax = LocalConfig.getInstance().getAreaKpiExl();
			break;
		case CARRIER_KPI:
			calculateTax = LocalConfig.getInstance().getCarrierKpiExl();
			break;
		case ROOM_KPI:
			calculateTax = LocalConfig.getInstance().getRoomKpiExl();
			break;
		case PRODUCT_KPI:
			calculateTax = LocalConfig.getInstance().getProductKpiExl();
			break;
		case IP_KPI:
			calculateTax = LocalConfig.getInstance().getIpKpiExl();
			break;
		}

		JexlExpression e = jexl.createExpression(calculateTax);
		JexlContext context = new MapContext();
		context.set("x", x);
		context.set("leftValue", leftValue);
		context.set("leftScore", leftScore);
		context.set("rightValue", rightValue);
		context.set("rightScore", rightScore);
		Number result = (Number) e.evaluate(context);
		return result.floatValue();

	}
}
