package com.jntsys;

import java.util.Properties;

public class AppPaths {
	private Properties allSysEnviVars;	// значения всех системных переменных окружения
	private String dirApp;				// корневая директория приложения
	private String dirAppData;			// директория хранения данных
	private String dirAppConf;			// директория конфигурационных файлов
	private String dirAppRes;			// директория ресурсов приложения
	private String dirAppTmpl;			// директория шаблонов
	
	public Properties getAllSysEnviVars( )	{ 
		for (Object key:allSysEnviVars.keySet( ) ) {
			System.out.println(key + "=" + allSysEnviVars.get( key ) );
		}
		return allSysEnviVars;
	}
	public String getDirApp( )		{ return this.dirApp; }
	public String getDirAppData( )	{ return this.dirAppData; }
	public String getDirAppConf( )	{ return this.dirAppConf; }
	public String getDirAppRes( )	{ return this.dirAppRes; }
	public String getDirAppTmpl( )	{ return this.dirAppTmpl; }
	
	public void setAllSysEnviVars(Properties allSysEnviVars) { this.allSysEnviVars = allSysEnviVars; }
	public void setDirApp(String dirApp)			{ this.dirApp = dirApp; }
	public void setDirAppData(String dirAppData)	{ this.dirAppData = dirAppData; }
	public void setDirAppConf(String dirAppConf)	{ this.dirAppConf = dirAppConf; }
	public void setDirAppRes(String dirAppRes)		{ this.dirAppRes = dirAppRes; }
	public void setDirAppTmpl(String dirAppTmpl)	{ this.dirAppTmpl = dirAppTmpl; }
    
}
