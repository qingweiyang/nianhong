package com.nianhong.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nianhong.dao.SubTaskDao;
import com.nianhong.dao.TaskDao;
import com.nianhong.dao.TaskGetDao;
import com.nianhong.dao.TaskTypeDao;
import com.nianhong.model.SalerTaskDoing;
import com.nianhong.model.SubTask;
import com.nianhong.model.Task;
import com.nianhong.model.WaitVerify;
import com.nianhong.service.SalerService;
import com.nianhong.vo.SubTaskVO;
import com.nianhong.vo.TaskVO;

@Service
public class SalerServiceImpl implements SalerService{

	@Autowired
	private TaskDao taskDao;
	@Autowired
	private SubTaskDao subTaskDao;
	@Autowired
	private TaskTypeDao taskTypeDao;
	@Autowired
	private TaskGetDao taskGetDao;
	
	@Override
	public boolean publishTask(String user, TaskVO taskvo) {
		Task task = new Task();
		task.setAdvanced(taskvo.getAdvanced());
		task.setBrief(taskvo.getBrief());
		task.setBuyer_freeze(taskvo.getBuyerFreeze());
		task.setCommission(taskvo.getCommission());
		task.setDetail(taskvo.getDetail());
		task.setPerson_need(taskvo.getPersonNeed());
		task.setReward(taskvo.getReward());
		task.setSaler_freeze(taskvo.getSalerFreeze());
		task.setTitle(taskvo.getTitle());
		task.setType(taskvo.getType());
		task.setVerify(taskvo.getVerify());
		//状态默认为正常（1）
		task.setStatus(1);
		
		//任务发布者姓名
		task.setPublisher(user);
		//获取系统（服务器）当前时间
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		task.setPublish_time(date);
		//系统利用时间戳生成任务的唯一id
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddHHmmss");
		String id = sf.format(date);
		task.setId(id);
		
		//将任务插入数据库
		taskDao.insertTask(task);
		
		//将子任务插入数据
		List<SubTaskVO> subTasks = taskvo.getSubtask();
		for(SubTaskVO subTask : subTasks) {
			SubTask st = new SubTask();
			st.setTask_id(id);
			st.setProvince(subTask.getProvince());
			st.setCity(subTask.getCity());
			st.setStart_time(subTask.getStartTime());
			st.setFinish_time(subTask.getFinishTime());
			st.setPerson_need(subTask.getSubNum());
			
			subTaskDao.insertSubTask(st);
		}
		
		return true;
	}

	@Override
	public List<String> loadType() {
		return taskTypeDao.selectAllType();
	}

	@Override
	public List<String> selectTypeByPrivilege(int pri) {
		return taskTypeDao.selectTypeByPrivilege(pri);
	}

	@Override
	public List<Task> getSalerTaskByStatus(int status) {
		return taskDao.selectByStatus(status);
	}

	@Override
	public int deletetTaskByID(String id) {
		return taskDao.deletetTaskByID(id);
	}

	@Override
	public List<WaitVerify> selectWaitVerifyTask(String publisher) {
		//选择用户未删除的所有任务
		List<Task> tasks = this.selectByPublisherAndStatus(publisher, 1);
		List<WaitVerify> res = new ArrayList<WaitVerify>();
		for(Task t : tasks) {
			WaitVerify wv = new WaitVerify();
			int persons = t.getPerson_need();
			//任务接受者数量
			int actNum = taskGetDao.selectWaitAcceptByTaskID(t.getId()).size();
			if(persons > actNum) {
				wv.setId(t.getId());
				wv.setPublish_time(t.getPublish_time());
				wv.setBrief(t.getBrief());
				res.add(wv);
			}
		}
		return res;
	}

	@Override
	public List<Task> selectByPublisherAndStatus(String publisher, int status) {
		return taskDao.selectByPublisherAndStatus(publisher, status);
	}

	@Override
	public List<SalerTaskDoing> selectDoingTaskModel(String username) {
		return taskDao.selectTaskDoingModel(username, 3, 4);
	}

	public void select(String publisher) {
//		List<Task> tasks = taskDao.selectByPublisher(publisher);
		
	}
	
	@Override
	public List<HashMap<String, Object>> selectSalerTask(String username, int status) {
		return taskDao.selectSalerTask(username, status);
	}

	@Override
	public boolean agreeAccepter(String taskID, String accepter) {
		return taskGetDao.updateStatusByTaskIDAndAccepter(taskID, accepter, 2);
	}

	@Override
	public boolean refuseAccepter(String taskID, String accepter) {
		//标记交易失败 ［雇主拒绝］
		taskGetDao.updateRemarkByTaskIDAndAccepter(taskID, accepter, "雇主拒绝");
		return taskGetDao.updateStatusByTaskIDAndAccepter(taskID, accepter, 5);
	}

	@Override
	public boolean suspendDeal(String taskGetID) {
		//标记交易失败 ［雇主中止交易］
		taskGetDao.updateRemarkByTaskGetID(taskGetID, "雇主中止交易");
		return taskGetDao.updateStatusByTaskGetID(taskGetID, 5);
	}

	@Override
	public boolean sureDeal(String taskGetID) {
		return taskGetDao.updateStatusByTaskGetID(taskGetID, 4);
	}
}
