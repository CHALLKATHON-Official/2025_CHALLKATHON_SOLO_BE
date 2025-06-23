package com.example.solo.schedule.controller.swaggerDocs.api;

import org.springframework.http.MediaType;

import com.example.solo.global.exception.BaseResponse;
import com.example.solo.schedule.controller.swaggerDocs.constant.AddScheduleApiConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class AddScheduleApiDocs {

  @Operation(summary = "일정 추가", description = "일정을 추가 합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "일정 추가 성공",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponse.class),
                    examples =
                        @ExampleObject(value = AddScheduleApiConstant.Add_Schedule_Success))),
      })
  public void addSchedule() {}
}
