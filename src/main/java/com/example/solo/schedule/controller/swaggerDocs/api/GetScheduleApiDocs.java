package com.example.solo.schedule.controller.swaggerDocs.api;

import org.springframework.http.MediaType;

import com.example.solo.global.exception.BaseResponse;
import com.example.solo.schedule.controller.swaggerDocs.constant.GetScheduleApiConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class GetScheduleApiDocs {

  @Operation(summary = "월별 일정 조회", description = "월별 일정을 조회합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "일정 조회 성공",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponse.class),
                    examples =
                        @ExampleObject(value = GetScheduleApiConstant.Get_Schedule_Success))),
      })
  public void getSchedule() {}
}
