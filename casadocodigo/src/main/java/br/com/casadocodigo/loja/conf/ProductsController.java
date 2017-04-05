package br.com.casadocodigo.loja.conf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Capa;
import br.com.casadocodigo.loja.models.Product;
import br.com.casadocodigo.loja.validation.ProductValidator;

@Controller
@Transactional
@ControllerAdvice
@RequestMapping("/products")
public class ProductsController {
	
	

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	@Qualifier("recurso")
	private Recurso recurso;

	@Autowired
	@Qualifier("teste")
	private ResourceLoaderAware teste;

	Connection conexao;

	@RequestMapping("/form")
	public ModelAndView form(/*@PathVariable("userName") String name*/) {
		ModelAndView modelAndView = new ModelAndView("products/form");
		return modelAndView;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/listaProdutos");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}
	@RequestMapping(value="/list1",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody List<Product> list1() {
		ModelAndView modelAndView = new ModelAndView("products/listaProdutos");
		modelAndView.addObject("products", productDAO.list());
		return productDAO.list();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("product")Product product,BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
			return "products/form";
		}
		productDAO.save(product);
		return "redirect:/products/list";
	}
	
	@RequestMapping(value = "/helloReport1", method = RequestMethod.GET)
	@ResponseBody
	public void getRpt1(HttpServletResponse response, HttpServletRequest request) throws JRException,
			IOException, ClassNotFoundException, SQLException, InterruptedException {

		Resource resource = recurso.getResource("classpath:/report1.jasper");
		InputStream is = resource.getInputStream();
		InputStream is1 = recurso.getResource("classpath:/cabecalho.jasper").getInputStream();
		Map<String, Object> params = new HashMap<>();
		params.put("cabecalho", is1);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				params, criaConexao());
		request.getSession().setAttribute("jasperPrint", jasperPrint);
		Thread.sleep(5000);
		System.out.println("oi");
		//response.setContentType("application/x-pdf");
		//response.setHeader("Content-disposition",
		//		"inline; filename=helloWorldReport.pdf");
		//ModelAndView modelAndView = new ModelAndView("products/listaProdutos");
		//final OutputStream outStream = response.getOutputStream();
		//JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		//return modelAndView;
	
	}
	
	@RequestMapping(value = "/helloReportAtual", method = RequestMethod.GET)
	@ResponseBody
	public void getRpt2(HttpServletResponse response, HttpServletRequest request) throws JRException,
			IOException, ClassNotFoundException, SQLException, InterruptedException {

		/*Resource resource = recurso.getResource("classpath:/report1.jasper");
		InputStream is = resource.getInputStream();
		InputStream is1 = recurso.getResource("classpath:/cabecalho.jasper").getInputStream();
		Map<String, Object> params = new HashMap<>();
		params.put("cabecalho", is1);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				params, criaConexao());
		request.getSession().setAttribute("jasperPrint", jasperPrint);*/
		
		JasperPrint jasperPrint= (JasperPrint) request.getSession().getAttribute("jasperPrint");

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition",
				"inline; filename=helloWorldReport.pdf");
		ModelAndView modelAndView = new ModelAndView("products/listaProdutos");
		final OutputStream outStream = response.getOutputStream();
		
		
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		//return modelAndView;
	
	}

	public Connection criaConexao() throws ClassNotFoundException, SQLException {
		String endereco = "localhost";
		String porta = "3306";
		String banco = "historico_carro";
		String usuario = "root";
		String senha = "root1";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://" + endereco
					+ ":" + porta + "/" + banco + "?user=" + usuario
					+ "&password=" + senha);
		} catch (ClassNotFoundException ex) {
			throw ex;
		} catch (SQLException ex) {
			throw ex;
		}
		return conexao;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new ProductValidator());
		binder.registerCustomEditor(Capa.class, new CartaTyeEditor());
	}
	@ModelAttribute("product")
	public Product getProduct(){
	    return new Product();
	}

	@ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("types", BookType.values());
    }

}