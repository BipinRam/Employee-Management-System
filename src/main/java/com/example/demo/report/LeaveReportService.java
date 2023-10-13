package com.example.demo.report;

import com.example.demo.entity.LeaveApplication;

import java.util.List;

public interface LeaveReportService {
    List<LeaveApplication> listAllLeaveApplication(LeaveApplication leaveApplication);

}
