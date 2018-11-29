package com.aotain.tdc.controller.serviceConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.util.SubnetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aotain.common.cache.CommonCache;
import com.aotain.common.util.ExcelUtils;
import com.aotain.common.util.LocalConfig;
import com.aotain.common.util.LoginInfoUtil;
import com.aotain.common.util.Tools;
import com.aotain.tdc.constant.CacheType;
import com.aotain.tdc.constant.ExportType;
import com.aotain.tdc.constant.ModuleType;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.dto.common.IpPortDTO;
import com.aotain.tdc.dto.common.PageResult;
import com.aotain.tdc.model.common.BaseModel;
import com.aotain.tdc.model.common.MsgBean;
import com.aotain.tdc.service.common.CommonService;
import com.aotain.tdc.service.serviceConfiguration.BusinessInformationService;
import com.aotain.tdc.service.serviceConfiguration.ServiceArchitectureService;

@Controller
@RequestMapping(value = "/serviceConfiguration/businessInformation")
public class BusinessInformationController {

	@Autowired
	private BusinessInformationService businessInformationService;
	
	@Autowired
	private CommonCache commonCache;
	
	@Autowired
	private ServiceArchitectureService serviceArchitectureService;
	
	@Autowired
	private CommonService commonService;

	private MsgBean msg = new MsgBean();

	@RequestMapping("/index")
	@Secured({ "ROLE_WELCOME" })
	public String index(HttpServletRequest request) {
		String[] menus = LoginInfoUtil.getMenu(request.getRequestURI());
		request.setAttribute("headMenu", menus[0]);
		request.setAttribute("leftMenu", menus[1]);
		return "serviceConfiguration/businessInformation";
	}

	// 查询数据
	@Secured({ "ROLE_QUERY_WEB_SERVICEINFO" })
	@RequestMapping(value = "/initStaTable", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public PageResult<HashMap<String, String>> initStaTable(BaseDTO dto) {
		dto.setStartRow(dto.getOffset());
		dto.setEndRow(dto.getLimit());
		dto.setQueryIds("");
		businessInformationService.getTableColumns(dto);
		@SuppressWarnings("unchecked")
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) dto.getResultList();
		PageResult<HashMap<String, String>> pageResult = new PageResult<HashMap<String, String>>(list, dto.getTotalCounts());
		return pageResult;
	}
	
	@Secured({ "ROLE_QUERY_WEB_SERVICEINFO_EXPORT" })
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	@ResponseBody
	public void export(BaseModel<HashMap<String, String>> baseModel, BaseDTO dto, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{
    	ExportType exportType = ExportType.valueOf("EXCEL");
		String fileName = ModuleType.WEB_SERVICEINFO.getDescription()
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ exportType.getSuffix();
		if ("FF".equals(commonService.getBrowser(request))) {  
            // 针对火狐浏览器处理方式不一样了  
        	fileName = new String(fileName.getBytes("UTF-8"),  "iso-8859-1") ;
        }  else{
        	fileName = URLEncoder.encode( fileName, "UTF-8");
        }
		dto.setIsPaging(0);
		dto.setIsCount(0);
		dto.setIsQueryDateTime(LocalConfig.getInstance().getIsQueryDateTime());
		businessInformationService.getTableColumns(dto);
		commonService.exportData(baseModel, dto, fileName, (List<HashMap<String, String>>)dto.getResultList(), response);
	}
	
	@Secured({ "ROLE_QUERY_WEB_SERVICEINFO_ADD" })
	@RequestMapping(value = "/insert", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean insert(BaseDTO dto) throws ParseException {
		try {
			businessInformationService.insert(dto);
			commonCache.refreshCache();
			msg.setFlag(0);
			msg.setMsg("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("添加失败！");
		}
		return msg;
	}

	@Secured({ "ROLE_QUERY_WEB_SERVICEINFO_DEL" })
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean delete(BaseDTO dto) throws ParseException {
		try {
			businessInformationService.delete(dto);
			commonCache.refreshCache();
			msg.setFlag(0);
			msg.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("删除失败！");
		}
		return msg;
	}

	@Secured({ "ROLE_QUERY_WEB_SERVICEINFO_EDIT" })
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean update(BaseDTO dto) throws ParseException {
		try {
			businessInformationService.update(dto);
			commonCache.refreshCache();
			msg.setFlag(0);
			msg.setMsg("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("更新失败！");
		}
		return msg;
	}

	@Secured({ "ROLE_QUERY_WEB_SERVICEINFO_IMPORT" })
	@RequestMapping(value = "/dataImport", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public MsgBean dataImport(HttpServletRequest request) throws ParseException {
		try {
			MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> mFiles = multipartHttpservletRequest.getFiles("dataFile");
			if (mFiles.size() <= 0) {
				msg.setFlag(-1);
				msg.setMsg("导入失败：请上传数据文件！");
			} else if ((!mFiles.get(0).getOriginalFilename().endsWith(".xlsx")) && (!mFiles.get(0).getOriginalFilename().endsWith(".xls"))) {
				msg.setFlag(-1);
				msg.setMsg("导入失败：文件格式错误！");
			} else if (mFiles.get(0).getSize() > LocalConfig.getInstance().getFileSize()) {
				msg.setFlag(-1);
				msg.setMsg("导入失败：文件超过" + LocalConfig.getInstance().getFileSize() + "B！");
			} else {
				MultipartFile mFile = mFiles.get(0);
				Map<Integer, Map<Integer, String[]>> map = ExcelUtils.readExcelFromStream(mFile.getInputStream());
				Set<Entry<Integer, Map<Integer, String[]>>> set = map.entrySet();
				Iterator<Entry<Integer, Map<Integer, String[]>>> sheets = set.iterator();
				List<BaseDTO> list = new ArrayList<BaseDTO>();
				List<IpPortDTO> ipportList = new ArrayList<IpPortDTO>();
				while (sheets.hasNext()) {
					Entry<Integer, Map<Integer, String[]>> entry = sheets.next();
					Map<Integer, String[]> lines = entry.getValue();
					for (int i = 1; i <= lines.size(); i++) {
						String[] line = lines.get(i);

						IpPortDTO dto = new IpPortDTO();
						System.out.println(line[3]);
						if( line[3].equals("南方基地3.1栋/407机房") ){
							dto.setQueryResourceMark(1);
						}

						dto.setQueryServerBuildId(commonCache.getIdByName(CacheType.BUILDIND, line[3].trim().split("/")[0], 0L));
						dto.setQueryServerRoomId(commonCache.getIdByName(CacheType.ROOM, line[3].trim().split("/")[1], dto.getQueryServerBuildId()));
						
						dto.setQueryCatalogId(commonCache.getIdByName(CacheType.CATALOG, line[0].trim(), 0L));
						dto.setQueryClassId(commonCache.getIdByName(CacheType.CLASS, line[1].trim(), dto.getQueryCatalogId()));
						dto.setQueryProductId(commonCache.getProductIdByName(line[2].trim(), dto.getQueryClassId(), dto.getQueryServerBuildId(), dto.getQueryServerRoomId() ));
						dto.setQueryModuleId(commonCache.getIdByName(CacheType.MODULE, line[4].trim(), dto.getQueryProductId()));
						
						if (dto.getQueryServerBuildId() == null) {
							dto.setQueryName(line[3].trim().split("/")[0]);
							commonService.insertServerBuild(dto);
							commonCache.refreshCache();
						}

						if (dto.getQueryServerRoomId() == null) {
							dto.setQueryName(line[3].trim().split("/")[1]);
							commonService.insertServerRoom(dto);
							commonCache.refreshCache();
						}

						if (dto.getQueryCatalogId() == null) {
							dto.setQueryName(line[0].trim());
							serviceArchitectureService.insertCatalog(dto);
							commonCache.refreshCache();
						}
						if (dto.getQueryClassId() == null) {
							dto.setQueryName(line[1].trim());
							serviceArchitectureService.insertClass(dto);
							commonCache.refreshCache();
						}
						if (dto.getQueryProductId() == null) {
							dto.setQueryName(line[2].trim());
							serviceArchitectureService.insertProduct(dto);
							commonCache.refreshCache();
						}
						if (dto.getQueryModuleId() == null) {
							dto.setQueryName(line[4].trim());
							serviceArchitectureService.insertModule(dto);
							commonCache.refreshCache();
						}

						dto.setQueryServiceInfoId(commonService.getWebServiceinfoSeqNextVal());
						dto.setQueryIpMappingId(commonService.getDicIpmappingSeqNextVal());
						// 设置ip信息
						if (line[5] == null) {
							msg.setFlag(-1);
							msg.setMsg("导入失败：请输入正确的IP地址！出错记录序号：" + i);
							return msg;
						}

						try {
							String[] ips = line[5].trim().split(",");

							for (String ip : ips) {
								String startIp = null;
								String endIp = null;
								if (ip.indexOf("~") > 0) {
									ip = ip.trim();
									startIp = ip.split("~")[0];
									endIp = ip.split("~")[1];
								} else if (ip.indexOf("-") > 0) {
									ip = ip.trim();
									startIp = ip.split("-")[0].trim();
									endIp = ip.split("-")[1].trim();
								} else if (ip.indexOf("/") > 0) {
									SubnetUtils utils = new SubnetUtils(ip);
									if (utils.getInfo().getHighAddress().equals("0.0.0.0")) {
										startIp = ip.split("/")[0].trim();
										endIp = ip.split("/")[0].trim();
									} else {
										startIp = utils.getInfo().getLowAddress();
										endIp = utils.getInfo().getHighAddress();
									}
								} else {
									startIp = ip.trim();
									endIp = ip.trim();
								}
								if ((!Tools.isIpAddress(startIp)) || (!Tools.isIpAddress(endIp))) {
									msg.setFlag(-1);
									msg.setMsg("导入失败：请输入正确的IP地址！出错记录序号：" + i);
									return msg;
								} else if (Tools.ip2long(startIp) > Tools.ip2long(endIp)) {
									msg.setFlag(-1);
									msg.setMsg("导入失败：起始IP地址不能大与结束IP地址！出错记录序号：" + i);
									return msg;
								}
								dto.setQueryStartIp(startIp);
								dto.setQueryEndIp(endIp);
								// 设置port信息
								if (line[6] == null) {
									msg.setFlag(-1);
									msg.setMsg("导入失败：请输入正确的端口号！出错记录序号：" + i);
									return msg;
								}
								if (line[6].trim().equalsIgnoreCase("any") || line[6].trim().equalsIgnoreCase("all")) {
									String startPort = "0";
									String endPort = "0";
									dto.setQueryIpPortId(commonService.getDicIpportSeqNextVal());
									dto.setQueryStartPort(startPort);
									dto.setQueryEndPort(endPort);
									IpPortDTO ipportDto = (IpPortDTO) Tools.cloneObject(dto);
									ipportList.add(ipportDto);
								} else {
									String[] ports = null;
									if (line[6].trim().indexOf("/") > 0) {
										ports = line[6].trim().split("/");
									} else if (line[6].trim().indexOf(",") > 0) {
										ports = line[6].trim().split(",");
									} else {
										ports = line[6].trim().split(" ");
									}
									for (String port : ports) {
										String startPort = null;
										String endPort = null;
										if (port.indexOf("~") > 0) {
											port = port.trim();
											startPort = port.split("~")[0];
											endPort = port.split("~")[1];
										} else if (port.indexOf("-") > 0) {
											port = port.trim();
											startPort = port.split("-")[0].trim();
											endPort = port.split("-")[1].trim();
										} else {
											port = port.trim();
											startPort = port;
											endPort = port;
										}
										if ((!(Tools.isNumber(startPort) && Integer.parseInt(startPort) >= 0))
												|| (!(Tools.isNumber(endPort) && Integer.parseInt(endPort) >= 0))) {
											msg.setFlag(-1);
											msg.setMsg("导入失败：请输入正确的端口号！出错记录序号：" + i);
											return msg;
										} else if (Integer.parseInt(startPort) > Integer.parseInt(endPort)) {
											msg.setFlag(-1);
											msg.setMsg("导入失败：起始端口号不能大与结束端口号！出错记录序号：" + i);
											return msg;
										}
										dto.setQueryIpPortId(commonService.getDicIpportSeqNextVal());
										dto.setQueryStartPort(startPort);
										dto.setQueryEndPort(endPort);
										IpPortDTO ipportDto = (IpPortDTO) Tools.cloneObject(dto);
										ipportList.add(ipportDto);
									}
								}
							}
						} catch (NullPointerException e) {
							e.printStackTrace();
							msg.setFlag(-1);
							msg.setMsg("导入失败：字段不能为空！出错记录序号：" + i);
							return msg;
						} catch (Exception e) {
							e.printStackTrace();
							msg.setFlag(-1);
							msg.setMsg("导入失败：请输入正确的IP地址！出错记录序号：" + i);
							return msg;
						}
						dto.setQueryDestIp(line[5].trim());
						dto.setQueryDestPort("0");
						list.add((BaseDTO)dto);
					}
				}
				// 批量添加ipmapping,serviceinfo,ipport
				list = new ArrayList<BaseDTO>(new HashSet<BaseDTO>(list));
				ipportList = new ArrayList<IpPortDTO>(new HashSet<IpPortDTO>(ipportList));
				businessInformationService.batInsert(list, ipportList);
				msg.setFlag(0);
				msg.setMsg("导入成功！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setFlag(-1);
			msg.setMsg("导入失败！");
		}
		return msg;
	}
}
