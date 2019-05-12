<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<head>
    <title>Calendar</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../resources/css/timepicker.min.css">
    <script src="../resources/js/timepicker.min.js"></script>


    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $(".datepicker").datepicker();
        });
    </script>
    <script>
        document.addEventListener("DOMContentLoaded", function (event) {

            timepicker.load({
                interval: 15,
                time: {
                    hour: null,
                    minute: null
                }

            });
        })


    </script>
    <style>

        .datepicker {

        }

        .button-save {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 6px 18px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            margin: 4px 2px;
            cursor: pointer;
        }

        .button-clear {
            position: relative;
            right: 75px;
            top: 0px;
            background-color: yellow;
            border: none;
            color: white;
            padding: 6px 18px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            margin: 4px 2px;
            cursor: pointer;
        }

        div.relative {
            position: relative;
            top: 7px;
            left: 73px;
        }

        .relative {
            position: relative;
            top: 0px;
            left: 60px;
        }
    </style>
</head>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>


<body>

<div class="container">
    <!-- Button to Open the Modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
        Add event
    </button>

    <!-- The Modal -->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Event Info (provide date or keyword for data parsing)</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">

                    <spring:form method="post" modelAttribute="event"
                                 action="${pageContext.request.contextPath}calendarAdd">
                        <div class="input-group" id="unified-inputs">
                            <table>
                                <tr>
                                    <td>Event</td>
                                    <td> <%session.setAttribute("title", "title");%><spring:input path="title"/></td>
                                </tr>

                                <tr>
                                    <td>Start date</td>
                                    <td><input type="text" class="datepicker" name="start-date" id="start-date"
                                               readonly="readonly" style="background:white;"/></td>
                                    <td>
                                        <div class="relative">Keyword</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Start time</td>
                                    <td><input type="text" data-toggle="timepicker" name="start-time" id="start-time"
                                               style="background:white;"/></td>
                                    <spring:form method="post" modelAttribute="dataParser"
                                                 action="${pageContext.request.contextPath}calendarAdd">
                                        <td><spring:input path="date" id="date" name="date" class="relative"
                                                          style="width: 50%;"/></td>
                                    </spring:form>
                                </tr>
                                <tr>
                                    <td>End date</td>
                                    <td><input type="text" class="datepicker" name="end-date" id="end-date"
                                               readonly="readonly" style="background:white;"/></td>

                                </tr>
                                <tr>
                                    <td>End time</td>
                                    <td><input type="text" data-toggle="timepicker" name="end-time" id="end-time"
                                               readonly="readonly" style="background:white;"/></td>
                                </tr>

                                <input type="text" name="start" style="display: none;"/>

                                <input type="text" name="end" style="display: none;"/>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td><input type="submit" value="Save" class="button-save" onclick=" onClick()"/>
                                    </td>
                                    <td><input type="reset" value="Clear" class="button-clear" onclick = "enableInputs()"></td>

                                </tr>
                            </table>
                        </div>

                    </spring:form>
                </div>

                <script>
                    function onClick() {

                        if ($('#date').val().length) {
                            remove();
                        }
                        else {
                            $().unifyInputs('start-date', 'start-time', 'start');
                            $().unifyInputs('end-date', 'end-time', 'end');
                            $('#date').remove();
                        }
                    }
                </script>

                <script>
                    function enableInputs(){
                        $('#start-date, #start-time, #end-date, #end-time, #date').prop('disabled', false);
                    }
                </script>
                <script>
                    function remove() {
                        $('#start-date, #start-time, #end-date, #end-time, input[name= "start"], input[name = "end"] ').remove();

                    }
                </script>
                <script>
                    $.fn.unifyInputs = function (Date, Time, final) {
                        $("input[name='" + Date + "']").hide();
                        $("input[name='" + Time + "']").hide();

                        $("input[name='" + final + "']").show();

                        $("input[name='" + final + "']").val($("input[name='" + Date + "']").val() + " " + $("input[name='" + Time + "']").val());
                    }
                </script>
                <script>
                    $('#date').on('input', function () {

                        if ($(this).val().length) {
                            $('#start-date, #start-time, #end-date, #end-time').prop('disabled', true);

                        } else
                            $('#start-date, #start-time, #end-date, #end-time').prop('disabled', false);


                    });

                </script>

                <script>
                    $("#start-date, #end-date").datepicker({

                        onSelect: function (dateText, inst) {

                            var date = $(this).val();

                            $('#date').prop('disabled', true);


                            $("#start").val(date);


                        }

                    });
                </script>
                <script>
                    $('#start-time, #end-time').on('click', function () {

                        $('#date').prop('disabled', true);
                    })
                </script>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
</div>

</body>


<script src="../resources/js/timepicker.min.js"></script>

</html>
