package com.weston.tools.validation.identity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**  
 *         <p>  
 *         类说明:提取身份证相关信息  
 *         </p>  
 */  
public class IDCardInfoExtractor {   
       
    private IDCardValidator validator = null;   
       
    /**  
     * 通过构造方法初始化各个成员属性  
     */  
    public IDCardInfoExtractor() {}   
    
    private IDCardInfo extractor(String idcard){
    	 
    	IDCardInfo idInfo=new IDCardInfo();
        try {   
            validator = IDCardValidator.getInstance();   
            if (validator.isValidatedAllIdcard(idcard)) {   
                if (idcard.length() == 15) {   
                    idcard = validator.convertIdcarBy15bit(idcard);   
                }   
                // 获取省份   
                String adminRegionCode=idcard.substring(0,6);
                //String name=AdminRegion.getInstance().getName(adminRegionCode);
                String province=Regions.getInstance().getProvince(adminRegionCode);
                String city=Regions.getInstance().getCity(adminRegionCode);
                String region=Regions.getInstance().getRegion(adminRegionCode);
                
                idInfo.setProvince(province);
                idInfo.setCity(city);
                idInfo.setRegion(region);
                // 获取性别   
                String id17 = idcard.substring(16, 17);   
                if (Integer.parseInt(id17) % 2 != 0) {  
                	idInfo.setGender(Gender.Male);
                } else {  
                	idInfo.setGender(Gender.Female);
                }   
  
                // 获取出生日期   
                String birthday = idcard.substring(6, 14);   
                Date birthdate = new SimpleDateFormat("yyyyMMdd")   
                        .parse(birthday);   
                idInfo.setBirthday(birthdate);
                GregorianCalendar currentDay = new GregorianCalendar();   
                currentDay.setTime(birthdate); 
                idInfo.setYear(currentDay.get(Calendar.YEAR));
                idInfo.setMonth(currentDay.get(Calendar.MONTH)+1);
                idInfo.setDay(currentDay.get(Calendar.DAY_OF_MONTH));
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return idInfo;
    }
  
    public static void main(String[] args) {   
        String[] idcard = {"430402198307050020"};   
        IDCardInfoExtractor ie = new IDCardInfoExtractor();   
        for (String id : idcard) {
        	if (IDCardValidator.getInstance().isValidate18Idcard(id)) {
				System.out.println("ID:"+id+" is validated");
				System.out.println(ie.extractor(id).toString());  
	        	System.out.println("-----------------------------------------------------------------------");
			}else{
				System.out.println("ID:"+id+" is illegal");
			}
		}
    }   
}  