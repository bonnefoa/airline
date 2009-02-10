/**
 * Copyright (C) 2009 Anthonin Bonnefoy and David Duponchel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xlsParser;

import jxl.read.biff.BiffException;
import xlsParser.parser.Handler;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Main method
 */
public class App {
    private static Handler handler;

    public static void main(String[] args) throws BiffException, IOException, SQLException {
        if (args.length < 2) {
            System.out.println("Usage :");
            System.out.println("[Script de creation sql de la base] [Donnees sous format xsl] ");
        } else {
            System.out.println("Parse :");
            handler = new Handler(args[0], args[1]);
            handler.execute();
            System.out.println("Parse finit");
        }
        System.exit(0);
    }
}
