<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='utf-8' />

    <link href='packages/core/main.css' rel='stylesheet' />
    <link href='packages/daygrid/main.css' rel='stylesheet' />
    <link href='packages/timegrid/main.css' rel='stylesheet' />
    <link href='packages/list/main.css' rel='stylesheet' />

    <script src='packages/core/main.js'></script>
    <script src='packages/daygrid/main.js'></script>
    <script src='packages/timegrid/main.js'></script>
    <script src='packages/list/main.js'></script>

    <script src='resources/js/moment.js'></script>

    <script src='https://unpkg.com/popper.js/dist/umd/popper.min.js'></script>
    <script src='https://unpkg.com/tooltip.js/dist/umd/tooltip.min.js'></script>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                plugins: ['dayGrid', 'timeGrid', 'list'],
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                },

                eventSources: [
                    {
                        url: 'http://localhost:8080/findAll',
                        timeZone: 'local',
                        dataType: 'jsonp',
                        type: "GET",
                        html: true,

                        success: function (data) {
                            for (var i = 0; i < data.length; i++) {
                                console.log(data[i].start, data[i].title, data[i].id);
                            }

                        }

                    },
                    {
                        url: 'http://localhost:8080/findAllDP',
                        color: 'yellow',
                        textcolor: 'black'
                    }
                ],

                // eventDataTransform: function (rawEventData) {
                //     return {
                //         id: rawEventData.id,
                //         title: rawEventData.title,
                //         start: rawEventData.start,
                //         end: rawEventData.end,
                //         url: rawEventData.url
                //     };
                // },


                eventClick: function (event) {
                    var el = $(this);

                    $.ajax({
                        type: 'GET',
                        url: 'http://localhost:8080/findAll',
                        // dataType: 'json',
                        success: function (data) {
                            for (var i = 0; i < data.length; i++) {
                                var title = data[i].title;
                                var start = data[i].start;
                                var end = data[i].end;
                                console.log(data[i].start, data[i].title, data[i].id, event.title, event.id);

                                alert('Event: ' + title + start + end + event.title + event.id);


                            }
                        }
                });
                },






                    // change the border color just for fun





                // eventRender: function(event, el) {
                //     $(el).popover({
                //         title: eventObj.title,
                //         trigger: 'hover',
                //         placement: 'top',
                //         container: 'body'
                //     });
                // }
                // eventRender: function(event, element) {
                //     element.find('.fc-content').attr("id", "event-" + event.id);
                // },
                //
                // eventClick: function(event, jsEvent, view) {
                //     var start = event.start;
                //     var end = event.end;
                //     var eventId = $(this).attr('id');
                //         $("#eventContent").dialog({modal: true, title: event.title, width: 350});
                //     $.ajax({
                //     url: 'http://localhost:8080/findAll',
                //     data: 'title='+ event.title +'&start='+ start +'&end='+ end,
                //     type: "POST",
                //     success: function(json) {
                //         alert('Added Successfully');
                //     }
                // })

            });

            calendar.render();
            // eventRender: function (event, element) {
            //     element.attr('href', 'javascript:void(0);');
            //     element.click(function() {
            //         $("#start").html(moment(event.start).format('MMM Do h:mm A'));
            //         $("#end").html(moment(event.end).format('MMM Do h:mm A'));
            //         // $("#eventInfo").html(event.description);
            //         $("#eventContent").dialog({ modal: true, title: event.title, width:350});
            //     });
            // }
        });

    </script>
    <script>
        function getcityvalue(id)
        {
            // this will generate another thread to run in another function
            jQuery.ajax({
                url: 'http://localhost:8080/findAll?id=' + id,
                type: 'get',
                dataType: 'text/html'
            });
        }
    </script>
    <style>
        body{
            margin: 40px 10px;
            padding: 0;
            font-family: "Lucida Grance", Helvetica,Arial,Verdana,sans-serif;
            font-size : 14px;
        }
        #calendar{
            max-width: 900px;
            margin: 0 auto;
        }
        .popper,
        .tooltip {
            position: absolute;
            z-index: 9999;
            background: #FFC107;
            color: black;
            width: 150px;
            border-radius: 3px;
            box-shadow: 0 0 2px rgba(0,0,0,0.5);
            padding: 10px;
            text-align: center;
        }
        .style5 .tooltip {
            background: #1E252B;
            color: #FFFFFF;
            max-width: 200px;
            width: auto;
            font-size: .8rem;
            padding: .5em 1em;
        }
        .popper .popper__arrow,
        .tooltip .tooltip-arrow {
            width: 0;
            height: 0;
            border-style: solid;
            position: absolute;
            margin: 5px;
        }

        .tooltip .tooltip-arrow,
        .popper .popper__arrow {
            border-color: #FFC107;
        }
        .style5 .tooltip .tooltip-arrow {
            border-color: #1E252B;
        }
        .popper[x-placement^="top"],
        .tooltip[x-placement^="top"] {
            margin-bottom: 5px;
        }
        .popper[x-placement^="top"] .popper__arrow,
        .tooltip[x-placement^="top"] .tooltip-arrow {
            border-width: 5px 5px 0 5px;
            border-left-color: transparent;
            border-right-color: transparent;
            border-bottom-color: transparent;
            bottom: -5px;
            left: calc(50% - 5px);
            margin-top: 0;
            margin-bottom: 0;
        }
        .popper[x-placement^="bottom"],
        .tooltip[x-placement^="bottom"] {
            margin-top: 5px;
        }
        .tooltip[x-placement^="bottom"] .tooltip-arrow,
        .popper[x-placement^="bottom"] .popper__arrow {
            border-width: 0 5px 5px 5px;
            border-left-color: transparent;
            border-right-color: transparent;
            border-top-color: transparent;
            top: -5px;
            left: calc(50% - 5px);
            margin-top: 0;
            margin-bottom: 0;
        }
        .tooltip[x-placement^="right"],
        .popper[x-placement^="right"] {
            margin-left: 5px;
        }
        .popper[x-placement^="right"] .popper__arrow,
        .tooltip[x-placement^="right"] .tooltip-arrow {
            border-width: 5px 5px 5px 0;
            border-left-color: transparent;
            border-top-color: transparent;
            border-bottom-color: transparent;
            left: -5px;
            top: calc(50% - 5px);
            margin-left: 0;
            margin-right: 0;
        }
        .popper[x-placement^="left"],
        .tooltip[x-placement^="left"] {
            margin-right: 5px;
        }
        .popper[x-placement^="left"] .popper__arrow,
        .tooltip[x-placement^="left"] .tooltip-arrow {
            border-width: 5px 0 5px 5px;
            border-top-color: transparent;
            border-right-color: transparent;
            border-bottom-color: transparent;
            right: -5px;
            top: calc(50% - 5px);
            margin-left: 0;
            margin-right: 0;
        }



        .removebtn {
            color:black;
            position: absolute;
            top: 0;
            right: 0;
            width:13px;
            height: 13px;
            text-align:center;
            border-radius:50%;
            font-size: 10px;
            cursor: pointer;
            background-color: #FFF
        }
    </style>
</head>
<body>
<jsp:include page="/fragments/header.jsp" />
<br><br>

<jsp:include page="/fragments/EventButton.jsp" />
<div id='calendar'></div>
<div id="eventContent" title="Event Details" style="display:none;">
    Start: <span id="start"></span><br>
    End: <span id="end"></span><br><br>
    <p id="eventInfo"></p>
    <p><strong><a id="eventLink" href="" target="_blank">Read More</a></strong></p>
</div>
</body>
</html>