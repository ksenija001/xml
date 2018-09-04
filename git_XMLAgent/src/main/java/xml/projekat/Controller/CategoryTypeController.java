package xml.projekat.Controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.projekat.Model.CategoryType;
import xml.projekat.Service.CategoryTypeService;

@RestController
@CrossOrigin(origins = {"http://localhost:9030"})
@RequestMapping(value = "/categoryTypes")
public class CategoryTypeController {

	@Autowired
	private CategoryTypeService categoryTypeService;

	// Vraca kategoriju smestaja
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CategoryType> getCategoryType(@PathVariable Long id) {
		System.out.println("\n GET CATEGORY TYPE:  " + id + "\n");
		CategoryType categoryType = categoryTypeService.findById(id);

		if (categoryType != null) {
			return new ResponseEntity<CategoryType>(categoryType, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Vraca kategorije smestaja
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<CategoryType>> getCategoryTypes() {
		System.out.println("\n GET CATEGORY TYPES " + "\n");
		ArrayList<CategoryType> categoryTypes = (ArrayList<CategoryType>) categoryTypeService.findAll();

		if (categoryTypes != null) {
			return new ResponseEntity<Collection<CategoryType>>(categoryTypes, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Kreira kategoriju smestaja
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ArrayList<CategoryType>> createCategoryType(@RequestBody String type) {
		System.out.println("\n CREATE CATEGORY TYPE " + "\n");

		ArrayList<CategoryType> categoryType = (ArrayList<CategoryType>) categoryTypeService.createCategoryType(type);

		if (categoryType != null) {
			return new ResponseEntity<ArrayList<CategoryType>>(categoryType, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// Brise kategoriju smestaja
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Collection<CategoryType>> deleteCategoryType(@PathVariable Long id) {
		System.out.println("\n DELETE CATEGORY TYPE:  " + id + "\n");
		ArrayList<CategoryType> deletedCategoryType = (ArrayList<CategoryType>) categoryTypeService.deleteCategoryType(id);

		if (deletedCategoryType != null) {
			return new ResponseEntity<Collection<CategoryType>>(deletedCategoryType, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Collection<CategoryType>> editCategoryType(@PathVariable Long id, @RequestBody String category) {
		System.out.println("\n EDIT CATEGORY TYPE:  " + id + "\n");
		ArrayList<CategoryType> editedCategoryType = (ArrayList<CategoryType>) categoryTypeService.editCategoryType(id, category);

		if (editedCategoryType != null) {
			return new ResponseEntity<Collection<CategoryType>>(editedCategoryType, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
