package com.example.solo.schedule.controller.swaggerDocs.constant;

public class GetScheduleApiConstant {

  public static final String Get_Schedule_Success =
      """
                   {
                     "isSuccess": true,
                     "code": "200",
                     "message": "요청 성공",
                     "divideCode": "20001",
                     "data": [
                       {
                         "duration": 5.5,
                         "date": "2025-06-23",
                         "category": "string"
                       },
                       {
                         "duration": 5.6666665,
                         "date": "2025-06-23",
                         "category": "test"
                       }
                     ]
                   }
              """;
}
