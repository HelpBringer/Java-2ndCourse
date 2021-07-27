<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h2>My Student Collection</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Surname</th>
                        <th>Name</th>
                        <th>Mark</th>
                        <th>Height</th>
                        <th>Compare with best</th>
                    </tr>
                    <xsl:variable name="max">
                        <xsl:for-each select="students_list/student/mark">
                            <xsl:sort select="." data-type="number" order="descending"/>
                            <xsl:if test="position() = 1">
                                <xsl:value-of select="."/>
                            </xsl:if>
                        </xsl:for-each>
                    </xsl:variable>
                    <tr>
                        <td/>
                        <td> Best mark:</td>
                        <td>
                            <xsl:value-of select="$max"/>
                        </td>
                        <td/>
                        <td/>
                    </tr>
                    <xsl:for-each select="students_list/student">
                        <xsl:variable name="mark" select="mark"/>
                        <tr>
                            <td><xsl:value-of select="surname"/></td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="mark"/></td>
                            <td><xsl:value-of select="height"/></td>

                            <td> <xsl:value-of select=" round(($mark div number($max)) * 10000) div 10000"/></td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>