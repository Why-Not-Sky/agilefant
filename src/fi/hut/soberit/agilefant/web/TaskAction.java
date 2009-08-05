package fi.hut.soberit.agilefant.web;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import fi.hut.soberit.agilefant.annotations.PrefetchId;
import fi.hut.soberit.agilefant.business.TaskBusiness;
import fi.hut.soberit.agilefant.business.TransferObjectBusiness;
import fi.hut.soberit.agilefant.model.Task;

@Component("taskAction")
@Scope("prototype")
public class TaskAction extends ActionSupport implements Prefetching, CRUDAction {

    private static final long serialVersionUID = 7699657599039468223L;
    
    // Services
    @Autowired
    private TaskBusiness taskBusiness;
    
    @Autowired
    private TransferObjectBusiness transferObjectBusiness;
    
    // Helper fields
    private Task task;
    @PrefetchId
    private int taskId;
    private Integer iterationId;
    private Integer storyId;

    private Set<Integer> userIds = new HashSet<Integer>();   

    // CRUD
    
    public String create() {
        setTask(new Task());
        return Action.SUCCESS;
    }
    
    public String store() {
        task = taskBusiness.storeTask(task, iterationId, storyId, userIds);
        populateJsonData();
        return Action.SUCCESS;
    }
    
    public String retrieve() {
        task = taskBusiness.retrieve(taskId);
        populateJsonData();
        return Action.SUCCESS;
    }
    
    public String delete() {
        task = taskBusiness.retrieve(taskId);
        taskBusiness.delete(task.getId());
        return Action.SUCCESS;
    }
    
    // OTHER FUNCTIONS
    
    public String move() {
        task = taskBusiness.retrieve(taskId);
        task = taskBusiness.move(task, iterationId, storyId);
        populateJsonData();
        return Action.SUCCESS;
    }
    
    public String resetOriginalEstimate() {
        task = taskBusiness.retrieve(taskId);
        task = taskBusiness.resetOriginalEstimate(taskId);
        populateJsonData();
        return Action.SUCCESS;
    }
    
    private void populateJsonData() {
        task = transferObjectBusiness.constructTaskTO(task);
    }
    
    // Prefetching
    
    public void initializePrefetchedData(int objectId) {
        task = taskBusiness.retrieve(objectId);
    }
    
      
    // AUTOGENERATED    
    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
    
    public void setTaskBusiness(TaskBusiness taskBusiness) {
        this.taskBusiness = taskBusiness;
    }

    public Set<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }
    
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTransferObjectBusiness(
            TransferObjectBusiness transferObjectBusiness) {
        this.transferObjectBusiness = transferObjectBusiness;
    }

    public void setIterationId(Integer iterationId) {
        this.iterationId = iterationId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }
}
