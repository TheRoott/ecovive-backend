package com.example.myapplication.data

data class AppStats(
    val totalUsers: Int = 100,
    val totalReports: Int = 50,
    val activeUsers: Int = 87,
    val reportsThisMonth: Int = 23
)

data class ReportData(
    val id: Int,
    val userName: String,
    val category: String,
    val description: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val date: String,
    val status: String
)

object MockData {
    val appStats = AppStats()
    
    val sampleReports = listOf(
        ReportData(1, "María González", "Basura", "Acumulación de basura en la esquina", "Calle María Rosa 23, Ventanilla", -12.0567, -77.1181, "2024-01-15", "Resuelto"),
        ReportData(2, "Carlos Mendoza", "Contaminación", "Vertido de aceite en la calle", "Av. Los Héroes 45, Ventanilla", -12.0543, -77.1156, "2024-01-14", "En proceso"),
        ReportData(3, "Ana Silva", "Basura", "Contenedores desbordados", "Jr. San Martín 78, Ventanilla", -12.0521, -77.1123, "2024-01-13", "Pendiente"),
        ReportData(4, "Roberto Torres", "Contaminación del Agua", "Desechos en el río", "Calle El Sol 12, Ventanilla", -12.0589, -77.1201, "2024-01-12", "Resuelto"),
        ReportData(5, "Lucía Herrera", "Basura", "Basura en el parque", "Plaza Central 34, Ventanilla", -12.0512, -77.1098, "2024-01-11", "En proceso"),
        ReportData(6, "Miguel Vargas", "Tala Ilegal", "Árboles cortados sin autorización", "Av. Progreso 67, Ventanilla", -12.0534, -77.1145, "2024-01-10", "Pendiente"),
        ReportData(7, "Carmen Ruiz", "Basura", "Basura en la playa", "Malecón 89, Ventanilla", -12.0498, -77.1076, "2024-01-09", "Resuelto"),
        ReportData(8, "Diego Morales", "Contaminación", "Humo negro de fábrica", "Calle Industrial 23, Ventanilla", -12.0567, -77.1212, "2024-01-08", "En proceso"),
        ReportData(9, "Patricia Castro", "Basura", "Basura en el mercado", "Mercado Central 56, Ventanilla", -12.0501, -77.1089, "2024-01-07", "Pendiente"),
        ReportData(10, "Fernando López", "Contaminación del Agua", "Aguas servidas en la calle", "Jr. Libertad 91, Ventanilla", -12.0545, -77.1167, "2024-01-06", "Resuelto"),
        ReportData(11, "Rosa Jiménez", "Basura", "Basura en la escuela", "Colegio San José 45, Ventanilla", -12.0523, -77.1134, "2024-01-05", "En proceso"),
        ReportData(12, "Antonio Rojas", "Maltrato Animal", "Perros abandonados", "Calle Las Flores 78, Ventanilla", -12.0578, -77.1198, "2024-01-04", "Pendiente"),
        ReportData(13, "Elena Díaz", "Basura", "Basura en el hospital", "Hospital San Juan 12, Ventanilla", -12.0515, -77.1102, "2024-01-03", "Resuelto"),
        ReportData(14, "Jorge Sánchez", "Contaminación", "Quema de basura", "Av. Principal 34, Ventanilla", -12.0556, -77.1178, "2024-01-02", "En proceso"),
        ReportData(15, "Isabel Moreno", "Basura", "Basura en la iglesia", "Iglesia San Pedro 67, Ventanilla", -12.0532, -77.1151, "2024-01-01", "Pendiente"),
        ReportData(16, "Ricardo Peña", "Sustancias Químicas", "Derrame de químicos", "Calle Química 89, Ventanilla", -12.0587, -77.1223, "2023-12-31", "Resuelto"),
        ReportData(17, "Mónica Flores", "Basura", "Basura en el centro comercial", "Centro Comercial 23, Ventanilla", -12.0508, -77.1095, "2023-12-30", "En proceso"),
        ReportData(18, "Alberto Vega", "Contaminación del Agua", "Aceite en el drenaje", "Jr. Agua Dulce 56, Ventanilla", -12.0547, -77.1162, "2023-12-29", "Pendiente"),
        ReportData(19, "Sandra Ramos", "Basura", "Basura en la universidad", "Universidad 78, Ventanilla", -12.0529, -77.1140, "2023-12-28", "Resuelto"),
        ReportData(20, "Héctor Gutiérrez", "Tala Ilegal", "Árboles talados en el parque", "Parque Central 45, Ventanilla", -12.0518, -77.1115, "2023-12-27", "En proceso"),
        ReportData(21, "Adriana Cruz", "Basura", "Basura en la estación", "Estación de Bus 91, Ventanilla", -12.0561, -77.1189, "2023-12-26", "Pendiente"),
        ReportData(22, "Oscar Medina", "Contaminación", "Ruido excesivo", "Calle Ruido 34, Ventanilla", -12.0537, -77.1156, "2023-12-25", "Resuelto"),
        ReportData(23, "Gloria Aguilar", "Basura", "Basura en la farmacia", "Farmacia San Luis 67, Ventanilla", -12.0505, -77.1087, "2023-12-24", "En proceso"),
        ReportData(24, "Víctor Romero", "Maltrato Animal", "Gatos maltratados", "Calle Los Gatos 12, Ventanilla", -12.0572, -77.1205, "2023-12-23", "Pendiente"),
        ReportData(25, "Natalia Herrera", "Basura", "Basura en la biblioteca", "Biblioteca Municipal 78, Ventanilla", -12.0526, -77.1137, "2023-12-22", "Resuelto"),
        ReportData(26, "Pablo Castillo", "Contaminación del Agua", "Desechos en el canal", "Canal Principal 56, Ventanilla", -12.0551, -77.1172, "2023-12-21", "En proceso"),
        ReportData(27, "Beatriz Morales", "Basura", "Basura en la comisaría", "Comisaría 23, Ventanilla", -12.0513, -77.1108, "2023-12-20", "Pendiente"),
        ReportData(28, "Raúl Torres", "Sustancias Químicas", "Fugas de gas", "Calle Gas 89, Ventanilla", -12.0583, -77.1218, "2023-12-19", "Resuelto"),
        ReportData(29, "Liliana Vargas", "Basura", "Basura en la posta", "Posta Médica 45, Ventanilla", -12.0539, -77.1148, "2023-12-18", "En proceso"),
        ReportData(30, "César Rojas", "Tala Ilegal", "Deforestación", "Zona Verde 67, Ventanilla", -12.0509, -77.1092, "2023-12-17", "Pendiente"),
        ReportData(31, "Teresa López", "Basura", "Basura en el banco", "Banco Central 34, Ventanilla", -12.0564, -77.1185, "2023-12-16", "Resuelto"),
        ReportData(32, "Manuel Díaz", "Contaminación", "Emisiones tóxicas", "Av. Industrial 78, Ventanilla", -12.0542, -77.1160, "2023-12-15", "En proceso"),
        ReportData(33, "Rocío Sánchez", "Basura", "Basura en la clínica", "Clínica San José 91, Ventanilla", -12.0517, -77.1118, "2023-12-14", "Pendiente"),
        ReportData(34, "Eduardo Moreno", "Maltrato Animal", "Aves enjauladas", "Calle Las Aves 23, Ventanilla", -12.0575, -77.1202, "2023-12-13", "Resuelto"),
        ReportData(35, "Claudia Peña", "Basura", "Basura en la iglesia", "Iglesia San José 56, Ventanilla", -12.0528, -77.1143, "2023-12-12", "En proceso"),
        ReportData(36, "Andrés Flores", "Contaminación del Agua", "Aguas negras", "Jr. Agua 78, Ventanilla", -12.0553, -77.1175, "2023-12-11", "Pendiente"),
        ReportData(37, "Martha Vega", "Basura", "Basura en la escuela", "Escuela Primaria 45, Ventanilla", -12.0507, -77.1090, "2023-12-10", "Resuelto"),
        ReportData(38, "Roberto Ramos", "Sustancias Químicas", "Pesticidas", "Calle Química 67, Ventanilla", -12.0581, -77.1215, "2023-12-09", "En proceso"),
        ReportData(39, "Silvia Gutiérrez", "Basura", "Basura en el mercado", "Mercado Nuevo 34, Ventanilla", -12.0535, -77.1153, "2023-12-08", "Pendiente"),
        ReportData(40, "Javier Cruz", "Tala Ilegal", "Árboles talados", "Parque Los Árboles 89, Ventanilla", -12.0510, -77.1105, "2023-12-07", "Resuelto"),
        ReportData(41, "Luz Medina", "Basura", "Basura en la plaza", "Plaza Mayor 12, Ventanilla", -12.0568, -77.1192, "2023-12-06", "En proceso"),
        ReportData(42, "Carlos Aguilar", "Contaminación", "Humo industrial", "Zona Industrial 78, Ventanilla", -12.0546, -77.1168, "2023-12-05", "Pendiente"),
        ReportData(43, "María Romero", "Basura", "Basura en la estación", "Estación Norte 56, Ventanilla", -12.0522, -77.1139, "2023-12-04", "Resuelto"),
        ReportData(44, "José Herrera", "Maltrato Animal", "Perros maltratados", "Calle Los Perros 23, Ventanilla", -12.0579, -77.1208, "2023-12-03", "En proceso"),
        ReportData(45, "Ana Castillo", "Basura", "Basura en el hospital", "Hospital Central 91, Ventanilla", -12.0514, -77.1109, "2023-12-02", "Pendiente"),
        ReportData(46, "Luis Morales", "Contaminación del Agua", "Desechos en el río", "Río Ventanilla 45, Ventanilla", -12.0557, -77.1179, "2023-12-01", "Resuelto"),
        ReportData(47, "Carmen Torres", "Basura", "Basura en la universidad", "Universidad Nacional 67, Ventanilla", -12.0531, -77.1146, "2023-11-30", "En proceso"),
        ReportData(48, "Pedro Vargas", "Sustancias Químicas", "Derrame de petróleo", "Calle Petróleo 34, Ventanilla", -12.0585, -77.1220, "2023-11-29", "Pendiente"),
        ReportData(49, "Rosa Rojas", "Basura", "Basura en la biblioteca", "Biblioteca Central 78, Ventanilla", -12.0525, -77.1136, "2023-11-28", "Resuelto"),
        ReportData(50, "Francisco López", "Tala Ilegal", "Deforestación masiva", "Zona Bosque 56, Ventanilla", -12.0503, -77.1085, "2023-11-27", "En proceso")
    )
}
