package com.example.solo.schedule.controller.swaggerDocs.api;

import org.springframework.http.MediaType;

import com.example.solo.global.exception.BaseResponse;
import com.example.solo.schedule.controller.swaggerDocs.constant.GetScheduleCalApiConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class GetScheduleCalApiDocs {

  @Operation(summary = "카테고리 별 조회", description = "카테고리별을 조회합니다.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "카테고리 별 조회 성공",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BaseResponse.class),
                    examples =
                        @ExampleObject(
                            value = GetScheduleCalApiConstant.Get_Schedule_Cal_Success))),
      })
  public void getScheduleCal() {}
}
