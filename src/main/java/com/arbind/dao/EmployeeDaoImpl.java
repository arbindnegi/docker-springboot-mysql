package com.arbind.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.arbind.dao.EmployeeDao;
import com.arbind.model.Employee;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	List<Employee> result=null;
	@Override
	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		result = new ArrayList<Employee>();

		for (Map<String, Object> row : rows) {
			Employee emp = new Employee();
			emp.setEmpId((String) row.get("empId"));
			emp.setEmpName((String) row.get("empName"));
			result.add(emp);
		}

		return result;
	}

	@Override
	public void insertEmployee(Employee employee) {
		String sql = "INSERT INTO employee " +
				"(empId, empName) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				employee.getEmpId(), employee.getEmpName()
		});
		
	}

	@Override
	public Employee findById(String id) {
		String SELECT_BY_ID_QUERY = "SELECT * FROM employee WHERE empId=?";
		
		return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, getMap(), id);
	}
	
	private RowMapper<Employee> getMap(){
        // Lambda block
        RowMapper<Employee> empMap = (rs, rowNum) -> {
            Employee emp = new Employee();
            emp.setEmpId(rs.getString("empId"));
            emp.setEmpName(rs.getString("empName"));
            return emp;
        };
        return empMap;
    }
}