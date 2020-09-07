package cn.nrzt.cps.archives.device;


public interface IDeviceWR {
	public String 	read(String address);
	public String 	write(String address,Object value);
}
