package services.interfaces;

import models.ActivityLog;

import java.util.List;

public interface ActivityLogService {
    boolean logAction(ActivityLog activityLog);
    List<ActivityLog> getLogsByUserId(int userId);
    List<ActivityLog> getLogsByTimeRange(String startTime, String endTime);
}
