<!-- upload.jsp -->
<%@page import="java.util.ArrayList"%>
<%@ page import="java.io.*" %>
<%!
    public byte[] getDataBytes(InputStream inputStream, int length) 
    {
        DataInputStream in = new DataInputStream(inputStream);
        int formDataLength = length;

        byte dataBytes[] = new byte[formDataLength];
        int byteRead = 0;
        int totalBytesRead = 0;
        try 
        {
            while (totalBytesRead < formDataLength) 
            {
                byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
                totalBytesRead += byteRead;
            }
        } 
        catch (IOException ex) 
        {
            
        }

        return dataBytes;
    }

    public String getFullname(String data) 
    {
        String result = data.substring(data.indexOf("fullname\"") + 10);
        result = result.substring(0, result.indexOf("-----")).trim();
        return result;
    }

    public String getEmail(String data) 
    {
        String result = data.substring(data.indexOf("email\"") + 7);
        result = result.substring(0, result.indexOf("-----")).trim();
        return result;
    }

    public String getPassword(String data) 
    {
        String result = data.substring(data.indexOf("password\"") + 10);
        result = result.substring(0, result.indexOf("-----")).trim();
        return result;
    }

    public String getDob(String data) 
    {
        String result = data.substring(data.indexOf("dob\"") + 5);
        result = result.substring(0, result.indexOf("-----")).trim();
        return result;
    }

    public String getFilename(String data) 
    {
        String result = data.substring(data.indexOf("filename=\"") + 10);
        result = result.substring(0, result.indexOf("\n"));
        result = result.substring(result.lastIndexOf("\\") + 1, result.indexOf("\"")).trim();
        return result;
    }
    
    public ArrayList<String> upload(byte[] dataBytes, String contentType, String directory)
    {
        String data = new String(dataBytes);
        ArrayList<String> result = new ArrayList<String>();
        result.add(getPassword(data));
        result.add(getEmail(data));
        result.add(getFullname(data));
        result.add(getDob(data));
        String saveFileName = getFilename(data);
        result.add(saveFileName);
        if (!saveFileName.equals("")) 
        {
            int lastIndex = contentType.lastIndexOf("=");
            String boundary = contentType.substring(lastIndex + 1, contentType.length());

            int pos;
            pos = data.indexOf("filename=\"");
            pos = data.indexOf("\n", pos) + 1;
            pos = data.indexOf("\n", pos) + 1;
            pos = data.indexOf("\n", pos) + 1;

            int boundaryLocation = data.indexOf(boundary, pos) - 4;
            int startPos = ((data.substring(0, pos)).getBytes()).length;
            int endPos = ((data.substring(0, boundaryLocation)).getBytes()).length;
            String realPath = getServletContext().getRealPath(directory + "/" + saveFileName);

            try
            {
                FileOutputStream fileOut = new FileOutputStream(realPath);
                fileOut.write(dataBytes, startPos, (endPos - startPos));
                fileOut.flush();
                fileOut.close();
            }
            catch (FileNotFoundException ex)
            {
            
            }
            catch (IOException ex)
            {
            
            }
        }
        
        return result;
    }
%>