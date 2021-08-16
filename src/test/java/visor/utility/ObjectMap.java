
package visor.utility;


	import java.util.Properties;

	import org.openqa.selenium.By;

	public class ObjectMap {
		
		public static Properties OR;
		
		public enum locator{
			id,name,classname,tagname,linktext,partiallinktext,xpath,cssselector;
		}
		
		public static By getLocator(String objPropDesc) throws Exception{
			
			String locatorType;
			String locatorValue;
			
			boolean hasLocatorType = objPropDesc.startsWith("|");
			
			if (hasLocatorType){
				locatorType = objPropDesc.substring(1, objPropDesc.indexOf("|",1));
				locatorValue = objPropDesc.substring(objPropDesc.indexOf("|",1)+1);
				System.out.println("locatortype_hasLocatorType:"+locatorType+".");
				System.out.println("locatorValue_hasLocatorType:"+locatorValue+".");
			}else{
				locatorType="xpath";
				locatorValue=objPropDesc;
			}
			
			String finallocator=locatorType.toLowerCase();
			switch (locator.valueOf(finallocator)){
			
			case id:				return By.id(locatorValue);
			case name:				return By.name(locatorValue);
			case classname:			return By.className(locatorValue);
			case tagname:			return By.tagName(locatorValue);
			case linktext:			return By.linkText(locatorValue);
			case partiallinktext:	return By.partialLinkText(locatorValue);
			case cssselector:		return By.cssSelector(locatorValue);
			case xpath:				return By.xpath(locatorValue);
			default:				throw new Exception("Unknown locator Type '" + locatorType + "' for Object '" + objPropDesc + "'");
			}

		}
		
		public static void LoadProperties(String strPath){
			
	/*    	String Path_OR = Constants.Path_OR;
			FileInputStream fs;
			try {
				fs = new FileInputStream(Path_OR);
				OR= new Properties(System.getProperties());
				OR.load(fs);
				fs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	*/
			
		}

	}




