package com.example.solo.schedule.controller.swaggerDocs.constant;

public class GetScheduleCalApiConstant {

  public static final String Get_Schedule_Cal_Success =
      """
              {
                        "isSuccess": true,
                        "code": "200",
                        "message": "요청에 성공하였습니다.",
                        "divideCode": "success",
                        "data": {
                          "scheduleCalResponse": {
                            "category": "string",
                            "timeSum": 9,
                            "average": 4.5,
                            "year": 2025,
                            "month": 6
                          },
                          "scheduleResponseList": [
                            {
                              "category": "string",
                              "time": "04:00:00",
                              "date": "2025-06-24"
                            },
                            {
                              "category": "string",
                              "time": "05:00:00",
                              "date": "2025-06-24"
                            }
                          ]
                        }
              }
            """;
}
