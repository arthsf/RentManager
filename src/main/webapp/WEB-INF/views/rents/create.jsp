<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp" %>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Reservations
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post"
                              action="${pageContext.request.contextPath}/rents/create">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="vehicle" class="col-sm-2 control-label">Voiture</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="vehicle" name="vehicle" onchange="checkVehicleAvailability()">
                                            <option value="">--S&eacute;lectionner un v&eacute;hicule--</option>
                                            <c:forEach items="${vehicules}" var="vehicule">
                                                <option value="${vehicule.id}">${vehicule.constructor}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="client" class="col-sm-2 control-label">Client</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="client" name="client">
                                            <c:forEach items="${clients}" var="client">
                                                <option value="${client.id}">${client.firstname} ${client.lastname}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="debut" class="col-sm-2 control-label">Date de debut</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="debut" name="debut" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="fin" class="col-sm-2 control-label">Date de fin</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="fin" name="fin" required>
                                    </div>
                                </div>
                                <div class="alert alert-danger" role="alert"
                                     id="warningSection" style="display: none">Pas plus de 7 jours de suite !
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right" id="add_btn" disabled="disabled">
                                    Ajouter
                                </button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>

<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>

<script>
    function checkVehicleAvailability() {
        let vehicleId = $('#vehicle').val();
        let debut = $('#debut').val();
        let fin = $('#fin').val();
        if (vehicleId && debut && fin) {
            $.ajax({
                url: "${pageContext.request.contextPath}/cars/availability",
                type: "POST",
                data: {
                    vehicleId: vehicleId,
                    debut: debut,
                    fin: fin
                },
                success: function (response) {
                    if (response.isAvailable) {
                        $('#add_btn').prop('disabled', false);
                        $('#warningSection').hide();
                    } else {
                        $('#add_btn').prop('disabled', true);
                        $('#warningSection').show();
                    }
                },
                error: function (xhr) {
                    console.log(xhr);
                }
            });
        }
    }

    $(function () {
        $('[data-mask]').inputmask()
    });

    $('#vehicle').on('change', () => {
        if ($('#vehicle').val() === "") {
            document.getElementById('add_btn').disabled = true;
        } else {
            if (resList.includes("Conflict")) {
                document.getElementById('add_btn').disabled = true;
            } else {
                document.getElementById('add_btn').disabled = false;
            }
        }
    });
    $('#debut').on('change', () => {
        if ($('#fin').val()) {
            let date1 = new Date($('#fin').val());
            let date2 = new Date($('#debut').val());
            let diffTime = Math.abs(date2 - date1);
            let diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
            if (diffDays > 7) {
                document.getElementById('add_btn').disabled = true;
                document.getElementById('warningSection').style.display = 'block';
            } else {
                document.getElementById('add_btn').disabled = false;
                document.getElementById('warningSection').style.display = 'none';
            }
        }
    });
    $('#fin').on('change', () => {
        if ($('#debut').val()) {
            let date1 = new Date($('#fin').val());
            let date2 = new Date($('#debut').val());
            let diffTime = Math.abs(date2 - date1);
            let diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
            if (diffDays > 7) {
                document.getElementById('add_btn').disabled = true;
                document.getElementById('warningSection').style.display = 'block';
            } else {
                document.getElementById('add_btn').disabled = false;
                document.getElementById('warningSection').style.display = 'none';
            }
        }
    });
</script>

</body>
</html>
