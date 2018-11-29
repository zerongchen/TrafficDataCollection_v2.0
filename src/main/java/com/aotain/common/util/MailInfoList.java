package com.aotain.common.util;

import java.util.Vector;

public class MailInfoList {
	
	private boolean locked = false;
    /** SaveMail消息模块实例 */
    static private MailInfoList _instance;
    /** SaveMail消息队列 */
    private Vector _vList;
    /** 线程同步控制确保模块仅有一个实例 */
    static synchronized public MailInfoList getInstance() {
            if (_instance == null) {
                    _instance = new MailInfoList();
            }
            return _instance;
    }
    /** 构造器，默认消息队列长度为1000 */
    private MailInfoList() {
            _vList=new Vector(1000);
    }
    /** 向消息队列添加消息 */
    public synchronized void add(MailInfo msg){
    	if(!locked)
            _vList.addElement(msg);
    }
    /** 向消息队列插入消息(添加到队列起始处) */
    public synchronized void insert(MailInfo msg){
    	if(!locked)
            _vList.add(0,msg);
    }
    /** 返回并删除消息队列起始处消息，若消息队列为空，返回空 */
    public synchronized MailInfo remove(){
            if(_vList.size()==0)return null;
            return (MailInfo)_vList.remove(0);
    }
    /** 返回消息队列长度 */
    public int getSize(){
           return _vList.size();
    }
    /** 锁定入队 **/
    public void lock(){
    	locked = true;
    }
    /** 解除锁定入队 **/
    public void unlock(){
    	locked = false;
    }
    
    public static class MailInfo{
    	private String to;
    	private String subject;
    	private String content;
    	
    	public MailInfo(){
    		
    	}
    	
    	public MailInfo(String _to,String _subject,String _content){
    		this.to = _to;
    		this.subject = _subject;
    		this.content = _content;
    	}

		public String getTo() {
			return to;
		}

		public String getSubject() {
			return subject;
		}

		public String getContent() {
			return content;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public void setContent(String content) {
			this.content = content;
		}
    	
    }
}