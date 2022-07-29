package com.example.gestiontaches.dto;

public class UserTaskDto {
    private Long taskId;
    private Long employeeId;

    public UserTaskDto() {
    }

    public UserTaskDto(Long taskId, Long employeeId) {
        this.taskId = taskId;
        this.employeeId = employeeId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
