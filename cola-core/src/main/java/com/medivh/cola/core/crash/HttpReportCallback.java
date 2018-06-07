package com.medivh.cola.core.crash;

import java.io.File;

public interface HttpReportCallback
{
	void uploadException2remote(File file);
}
