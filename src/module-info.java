module Final_Project {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires org.hibernate.orm.core;
	requires java.persistence;
	requires java.sql;
	opens application to javafx.graphics, javafx.fxml,org.hibernate.orm.core;
}
