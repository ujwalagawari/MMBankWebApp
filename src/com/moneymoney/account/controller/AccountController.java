package com.moneymoney.account.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.account.service.SavingsAccountServiceImpl;
import com.moneymoney.account.util.DBUtil;
import com.moneymoney.account.util.SortAccounts;
import com.moneymoney.exception.AccountNotFoundException;

//@WebServlet("/app/*")
@WebServlet("*.mm")
public class AccountController extends HttpServlet {

	private SavingsAccountServiceImpl savingsAccountService;
	private RequestDispatcher dispatcher;
	private boolean flag;
	
	@Override
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bankapp_db", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		savingsAccountService = new SavingsAccountServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = request.getServletPath();
		int accountNumber = 0;
		double amount = 0;
		switch (path) {
		case "/addNewSA.mm":
			response.sendRedirect("addNewSAForm.jsp");
			break;
		case "/addNew.mm":
			String accountHolderName = request.getParameter("txtAccHN");
			double accountBalance = Double.parseDouble(request.getParameter("txtBalance"));
			boolean salary = request.getParameter("rdSalary").equalsIgnoreCase("yes")?true:false;

			try {
				if(request.getParameter("accountNumber") != ""){
					int accountNo = Integer.parseInt(request.getParameter("accountNumber"));
					String name = request.getParameter("txtAccHN");
					SavingsAccount account = savingsAccountService.getAccountById(accountNo);
					account.setSalary(salary);
					account.getBankAccount().setAccountHolderName(name);
					savingsAccountService.updateAccount(account, accountNo);
				}else{
					savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
				}
				response.sendRedirect("getAll.mm");
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				e.printStackTrace();
			}
			
			break;
		case "/searchForm.mm":
			response.sendRedirect("SearchForm.jsp");
			break;
		case "/search.mm":
			 accountNumber = Integer.parseInt(request.getParameter("txtAccountNumber"));
			try {
				SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
				request.setAttribute("account", account);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "/getAll.mm":
			try {
				List<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
				request.setAttribute("accounts", accounts);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/sortByNumber.mm":
			flag=!flag;
			try {
				List<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
				if(flag){
					SortAccounts.sortByAccountNumber("Ascending", accounts);
				}else{
					SortAccounts.sortByAccountNumber("Descending", accounts);
				}
				request.setAttribute("accounts", accounts);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "/sortByBalance.mm":
			flag=!flag;
			try {
				List<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
				if(flag){
					SortAccounts.sortByAccountBalance("Ascending", accounts);
				}else{
					SortAccounts.sortByAccountBalance("Descending", accounts);
				}
				request.setAttribute("accounts", accounts);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "/sortByName.mm":
			flag=!flag;
			try {
				List<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
				if(flag){
					SortAccounts.sortByAccountHolderName("Ascending", accounts);
				}else{
					SortAccounts.sortByAccountHolderName("Descending", accounts);
				}
				request.setAttribute("accounts", accounts);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e2) {
				e2.printStackTrace();
			}
			
			/*try {
				Collection<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
				Set<SavingsAccount> accountSet = new TreeSet<>(new Comparator<SavingsAccount>() {
					@Override
					public int compare(SavingsAccount arg0, SavingsAccount arg1) {
						return arg0.getBankAccount().getAccountHolderName().compareTo
								(arg1.getBankAccount().getAccountHolderName());
					}
				});
				accountSet.addAll(accounts);
				request.setAttribute("accounts", accountSet);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}*/
			
			break;
		case "/sortBySalary.mm":
			flag=!flag;
			try {
				Collection<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
				Set<SavingsAccount> accountSet = new TreeSet<>(new Comparator<SavingsAccount>() {
					@Override
					public int compare(SavingsAccount accountOne, SavingsAccount accountTwo) {
						if(flag){
							return accountOne.getBankAccount().getAccountHolderName().compareTo(accountTwo.getBankAccount().getAccountHolderName());
						}else{
							return accountTwo.getBankAccount().getAccountHolderName().compareTo(accountOne.getBankAccount().getAccountHolderName());	
						}
					}
				});
				accountSet.addAll(accounts);
				request.setAttribute("accounts", accountSet);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/updateSA.mm":
			response.sendRedirect("updateSA.jsp");
			break;
		case "/updateSAccount.mm":
			accountNumber = Integer.parseInt(request.getParameter("txtAccountNumber"));
			try {
				SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
				request.setAttribute("account", account);
				dispatcher = request.getRequestDispatcher("addNewSAForm.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "/closeAccount.mm":
			response.sendRedirect("closeAccount.jsp");
			break;
		case "/closeSaAccount.mm":
			accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
			try {
				savingsAccountService.deleteAccount(accountNumber);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				response.sendRedirect("getAll.mm");
				e.printStackTrace();
			}
			break;
		case "/getCurrentBalance.mm":
			response.sendRedirect("getCurrentBalance.jsp");
			break;
		case "/getCurrentBalanceOfSa.mm":
			accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
			try {
				double currentBalance = savingsAccountService.getCurrentBalance(accountNumber);
				response.getWriter().print("Hi "+accountNumber+" a/c number your current balance is "+currentBalance);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "/withdraw.mm":
			response.sendRedirect("withdraw.jsp");
			break;
		case "/withdrawFromSa.mm":
			accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		    amount = Double.parseDouble(request.getParameter("amount"));
			try {
				SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
				savingsAccountService.withdraw(savingsAccount, amount);
				DBUtil.commit();
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				try {
					DBUtil.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (Exception e) {
				try {
					DBUtil.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case "/deposit.mm":
			response.sendRedirect("deposit.jsp");
			break;
		case "/depositInSA.mm":
			accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
			amount = Double.parseDouble(request.getParameter("amount"));
			try {
				SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
				savingsAccountService.deposit(savingsAccount, amount);
				DBUtil.commit();
			} catch (ClassNotFoundException | SQLException e) { 
				e.printStackTrace();
				try {
					DBUtil.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				try {
					DBUtil.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case "/fundTransfer.mm":
			response.sendRedirect("fundTransfer.jsp");
			break;
		case "/fundTransferInSA.mm":
			int senderAccountNumber = Integer.parseInt(request.getParameter("senderAccountNumber"));
			int receiverAccountNumber = Integer.parseInt(request.getParameter("receiverAccountNumber"));
			amount = Double.parseDouble(request.getParameter("amount"));
			try {
				SavingsAccount senderSavingsAccount = savingsAccountService.getAccountById(senderAccountNumber);
				SavingsAccount receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
				savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}

}
